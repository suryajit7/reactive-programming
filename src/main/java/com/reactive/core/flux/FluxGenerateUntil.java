package com.reactive.core.flux;

import com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateUntil {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    synchronousSink.next(country);
                    if (country.equalsIgnoreCase("Canada")){
                        synchronousSink.complete();
                    }
                }).log()
                .take(10)
                .log()
                .subscribe(Util.subscriber());

        System.out.println("***********************************************");

        Flux.generate(() -> 1, (counter, sink) -> {
                    String country = Util.faker().country().name();
                    sink.next(country);
                    if (country.equalsIgnoreCase("Canada")){
                        sink.complete();
                    } return counter +1;
        }).log()
                .take(3)
                .log()
                .subscribe(Util.subscriber());
    }
}
