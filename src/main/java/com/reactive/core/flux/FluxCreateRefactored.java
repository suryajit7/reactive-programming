package com.reactive.core.flux;

import com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class FluxCreateRefactored {

    public static void main(String[] ags) {

        NameProducer nameProducer = new NameProducer();

        Flux.create(nameProducer).subscribe(Util.subscriber());

        Runnable runnable = nameProducer::produce;

        for (int i = 0; i < 10; i++){
            new Thread(runnable).start();
        }

    }
}
