package com.reactive.util;

import com.github.javafaker.Faker;
import com.reactive.core.DefaultSubscriber;
import org.reactivestreams.Subscriber;

import java.util.function.Consumer;

public class Util {


    public static final Faker FAKER = Faker.instance();

    public static Consumer<Object> onNext(){
        return object -> System.out.println("Received: " + object);
    }

    public static Consumer<Throwable> onError(){
        return error -> System.out.println("Error: " + error.getMessage());
    }

    public static Runnable onComplete(){
        return () -> System.out.println("Completed");
    }

    public static Faker faker(){
        return FAKER;
    }

    public static void sleepSeconds(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Subscriber<Object> subscriber() {
        return new DefaultSubscriber();
    }

    public static Subscriber<Object> subscriber(String name) {
        return new DefaultSubscriber(name);
    }

}
