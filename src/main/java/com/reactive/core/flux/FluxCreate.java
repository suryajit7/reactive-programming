package com.reactive.core.flux;

import com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class FluxCreate {

    public static void main(String[] ags) {
        Flux.create(fluxSink -> {

            fluxSink.next(1);
            fluxSink.next(2);
            fluxSink.complete();

        }).subscribe(Util.subscriber());


        Flux.create(fluxSink -> {

            String country;
            do {
                country = Util.faker().country().name();
                fluxSink.next(country);
            }while (!country.equalsIgnoreCase("Canada"));

            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }
}
