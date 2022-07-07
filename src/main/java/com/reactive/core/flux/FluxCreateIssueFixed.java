package com.reactive.core.flux;

import com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class FluxCreateIssueFixed {

    public static void main(String[] ags) {

        Flux.create(fluxSink -> {

            String country;
            do {
                country = Util.faker().country().name();
                System.out.println("Emitting countries: " + country);
                fluxSink.next(country);
            } while (!country.equalsIgnoreCase("Canada") && !fluxSink.isCancelled());

            fluxSink.complete();
        }).take(3)
                .subscribe(Util.subscriber());
    }
}
