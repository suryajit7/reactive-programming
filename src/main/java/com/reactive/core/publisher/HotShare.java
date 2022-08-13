package com.reactive.core.publisher;

import com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class HotShare {

    public static void main(String[] args) {

        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(2))
                .share();

        movieStream.subscribe(Util.subscriber("Surya"));
        Util.sleepSeconds(5);

        movieStream.subscribe(Util.subscriber("Venkat"));
        Util.sleepSeconds(15);

        movieStream.subscribe(Util.subscriber("Pranav"));
        Util.sleepSeconds(5);

    }

    private static Stream<String> getMovie(){
        System.out.println("Got movie streaming request...");
        return Stream.of("Scene 1", "Scene 2", "Scene 3", "Scene 4", "Scene 5", "Scene 6", "Scene 7");
    }

}
