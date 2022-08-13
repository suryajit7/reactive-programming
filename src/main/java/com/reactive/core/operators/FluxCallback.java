package com.reactive.core.operators;

import com.reactive.util.Util;
import reactor.core.publisher.Flux;

import static java.lang.System.out;

/**
 * Also called as Do Hooks/Callbacks
 */
public class FluxCallback {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> synchronousSink.next(Util.faker().country().name())).log()
                .map(Object::toString).log()
                .handle((str, synchronousSink) -> {
                    synchronousSink.next(str);
                    if (str.equalsIgnoreCase("Canada"))
                        synchronousSink.complete();
                }).log()
                .take(5).log()
                .doFirst(() -> out.println("doFirst...")).log()
                .doOnEach(signal -> out.println("doEach: " + signal)).log()
                .doOnNext(Object::notifyAll).log()
                .doOnNext(next -> out.println("doOnNext")).log()
                .doOnComplete(() -> out.println("doOnComplete...")).log()
                .doOnError(error -> out.println("doOnError..." + error.getMessage())).log()
                .doOnCancel(() -> out.println("doOnCancel...")).log()
                .doOnDiscard(Object.class, Object::notify).log()
                .doOnTerminate(() -> out.println("doOnTerminate...")).log()
                .doAfterTerminate(() -> out.println("doAfterTerminate...")).log()
                .doFinally(signalType -> out.println("doFinally: "+ signalType)).log()
                .subscribe(Util.subscriber());
    }




}
