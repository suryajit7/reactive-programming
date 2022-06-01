package com.reactive.core;

import com.reactive.util.Util;
import reactor.core.publisher.Mono;

public class MonoPublisher {


    public static void main(String[] args) {

        myRepo(1).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

        Mono<String> mono = Mono.just(getName());
    }

    private static Mono<String> myRepo(int myID){
        if (myID == 1){
            return Mono.just(Util.faker().name().firstName());
        } else if (myID == 2) {
            return Mono.empty();
        } else
            return Mono.error(new RuntimeException("Incorrect Value"));
    }

    public static String getName(){
        System.out.println("Generating name... ");
        return Util.faker().name().name();
    }



}
