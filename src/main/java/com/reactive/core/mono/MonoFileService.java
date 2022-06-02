package com.reactive.core.mono;

import com.reactive.util.Util;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.nio.file.Files.readAllLines;

public class MonoFileService {


    public static void main(String[] args) {

        Mono.fromFuture(readFile())
                .subscribe(Util.onNext(),
                        Util.onError());



        Util.sleepSeconds(1);

    }

    private static CompletableFuture<List<String>> readFile(){
        return CompletableFuture.supplyAsync(() -> {
            try {
                return readAllLines(Path.of("src/main/resources/test.txt"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

/*    @SneakyThrows
    public static Mono<List<String>> doFileOperations(){
        System.out.println("Performing File Operations...");
        return Mono.fromSupplier(() -> {

            try {
                writeString(Path.of("src/main/resources/test.txt"), "SomeString");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }*/

}
