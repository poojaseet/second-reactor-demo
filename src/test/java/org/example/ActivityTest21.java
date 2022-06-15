package org.example;

import org.junit.jupiter.api.Test;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ActivityTest21 {

    private List<String> dataValues = Arrays.asList("The", "quick", "brown", "fox", "jump", "over", "the", "lazy", "dog");

    @Test
    public void shouldAnswerWithtrue(){
       Flux<Integer> publisher = Flux.range(1,100);
        Disposable a = publisher.subscribe(System.out::println);
    }


    @Test
    public void simpleSubscriptionWithError(){
        Flux<Integer> numberSeq = Flux.range(1,20)
                .map(e -> {if(e==8) throw new RuntimeException("error on eights");
                  return e;});
        numberSeq.subscribe(suc -> System.out.printf("On Successb %s%n", suc), err -> System.err.printf("On Error %s ",err));
    }

    @Test
    public void subscriptionWithCancellation(){
      Flux<Integer> numberSeq = Flux.range(1,20).delayElements(Duration.ofSeconds(1));
        Disposable cancelRef = numberSeq.subscribe( e-> System.out.printf("value received %s%n",e),
                error -> System.err.println("Error published : " + error),
                () -> System.out.println("Complete event published"));
       Runnable runnableTask = () -> {
            try{
                TimeUnit.SECONDS.sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Cancelling subscription");
            cancelRef.dispose();
        };
       runnableTask.run();
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


    @Test
    void demoFluxPublishing(){
        Flux.just(this.dataValues).subscribe(System.out::println);
    }

    @Test
    void basicFluxSequenceIterativeTest(){
     //   StepVerifier.create(this.dataValues)
    }
}
