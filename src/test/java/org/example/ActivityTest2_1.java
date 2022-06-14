package org.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ActivityTest2_1 {

    @Test
    public void shouldAnswerWithtrue(){
       Flux<Integer> publisher = Flux.range(1,100);
        publisher.subscribe(System.out::println);
    }

    @Test
    public void publisherWithStream(){
        AtomicInteger ctr = new AtomicInteger();
        Flux.fromStream(Stream.generate(UUID::randomUUID).limit(10))
                .publishOn(Schedulers.boundedElastic())
                .map(u -> {
                    System.out.printf("found on %s%n", Thread.currentThread().getName());
                    return u+"hghghgh";
                })
                .subscribe(c -> {
                    System.out.printf("%s published  %s%n", ctr.getAndIncrement(), c);
                });


    }

}
