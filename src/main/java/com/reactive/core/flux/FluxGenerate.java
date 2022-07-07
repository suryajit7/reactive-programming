package com.reactive.core.flux;

import com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class FluxGenerate {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            synchronousSink.next(Util.faker().country().name());
         }).log()
                .take(3)
                .log()
                .subscribe(Util.subscriber());
    }
}
