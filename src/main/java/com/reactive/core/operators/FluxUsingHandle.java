package com.reactive.core.operators;

import com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class FluxUsingHandle {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> synchronousSink.next(Util.faker().country().name()))
                .map(Object::toString)
                .handle((str, synchronousSink) -> {

                    synchronousSink.next(str);

                    if (str.equalsIgnoreCase("Canada"))
                        synchronousSink.complete();

                })
                .subscribe(Util.subscriber());
    }
}
