package com.reactive.core.implement;

import com.reactive.core.service.FluxFileReaderService;
import com.reactive.util.Util;

import java.nio.file.Path;

public class FileServiceImplement {

    public static void main(String[] args) {

        FluxFileReaderService fluxFileReaderService = new FluxFileReaderService();

        Path filepath = Path.of("src/main/resources/test.txt");

        fluxFileReaderService.read(filepath)
                .take(20)
                .subscribe(Util.subscriber());

        System.out.println("**************************************");

        fluxFileReaderService.read(filepath)
                   .map(s -> {
                       Integer integer = Util.faker().random().nextInt(0, 10);
                       if (integer > 8)
                           throw new RuntimeException("oops");
                       return s;
                   })
                .take(20)
                .subscribe(Util.subscriber());
    }
}
