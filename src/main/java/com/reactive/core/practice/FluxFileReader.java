package com.reactive.core.practice;

import com.reactive.util.Util;
import lombok.SneakyThrows;
import reactor.core.publisher.Flux;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FluxFileReader {

    @SneakyThrows
    public static void main(String[] args) {

        Path filepath = Path.of("src/main/resources/test.txt");

        List<String> linesFromFile = Files.readAllLines(filepath);

        Flux.generate(() -> 1, (counter, sink) -> {

            if (counter < linesFromFile.size()){
                sink.next(linesFromFile.get(counter));
            } else {
                sink.complete();
            } return counter +1;
        }).log().subscribe(Util.subscriber());

    }




}
