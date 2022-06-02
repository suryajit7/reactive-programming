package com.reactive.core.mono;

import com.reactive.util.Util;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

public class MonoFromSupplier {

    public static void main(String[] args) {

        Supplier<String> monoSupplier = MonoFromSupplier::getName;
        Mono<String> mono = Mono.fromSupplier(monoSupplier);

        mono.subscribe(Util.onNext());

        getUppercaseName();
        getUppercaseName();
        getUppercaseName();

        getUppercaseName().subscribe(Util.onNext());

    }

    public static String getName(){
        System.out.println("Generating name... ");
        return Util.faker().name().name();
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
