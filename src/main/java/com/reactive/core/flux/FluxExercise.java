package com.reactive.core.flux;

import com.reactive.util.ReactiveSources;
import lombok.SneakyThrows;

public class FluxExercise {

    @SneakyThrows
    public static void main(String[] args) {

        ReactiveSources.intNumbersFlux()
                .subscribe(num -> System.out.println(num));

        ReactiveSources.userFlux()
                .subscribe(user -> System.out.println(user));

        System.out.println("Press any key...");
        System.in.read();

    }



}
