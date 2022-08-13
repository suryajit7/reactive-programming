package com.reactive.core.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class FluxFileReaderService {

    private Callable<BufferedReader> openReader(Path path){
        return () -> Files.newBufferedReader(path);
    }

    private BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> read() {
        return ((bufferedReader, sink) -> {
            try {
                String line = bufferedReader.readLine();
                System.out.println("reading line by line ...");
                if (Objects.isNull(line))
                    sink.complete();
                else
                    sink.next(line);
            } catch (IOException e) {
                sink.error(e);
            }
            return bufferedReader;
        });
    }

    public Consumer<BufferedReader> closeReader() {
        return bufferedReader -> {
            try{
                bufferedReader.close();
                System.out.println("*** closed ***");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public Flux<String> read(Path path){
        return Flux.generate(
                openReader(path), read(), closeReader()
        );
    }

}
