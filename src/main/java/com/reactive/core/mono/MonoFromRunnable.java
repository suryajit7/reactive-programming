package com.reactive.core.mono;

import com.reactive.util.Util;
import reactor.core.publisher.Mono;

public class MonoFromRunnable {

    public static void main(String[] args) {

        Mono.fromRunnable(getName())
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        () -> System.out.println("Kuch bhi +++"));

        Util.sleepSeconds(1);

    }

    private static Runnable getName(){
        return () -> {
        System.out.println("Kuch bhi...");
        };
    }


    public static Mono<String> getUppercaseName(){
        System.out.println("Generating uppercase name...");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name from Mono Supplier...");
            Util.sleepSeconds(1);
            return Util.faker().name().name();
        }).map(String::toUpperCase);
    }


}
