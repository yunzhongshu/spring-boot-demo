package com.zmdj.demo.explore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

import static org.junit.Assert.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



/**
 * @author zhangyunyun create on 2019/3/1
 */
@RunWith(MockitoJUnitRunner.class)
public class ReactiveTest {


    @Test
    public void mono() {

        Mono<String> mono1 = Mono.just("mono1");

        System.out.println(mono1.block());

        mono1.flatMap(new Function<String, Mono<?>>() {
            @Override
            public Mono<?> apply(String s) {
                 System.out.println(s);
                 return null;
            }
        });

        mono1.map(new Function<String, Object>() {
            @Override
            public Object apply(String s) {
                return null;
            }
        });

        mono1.then();

    }

    @Test
    public void fluxStatic() {

        Flux<String> flux1 = Flux.just("first", "second", "third");

        assertTrue(flux1.blockFirst().equals("first"));

        assertTrue(flux1.blockLast().equals("third"));

        Flux<String> flux2 = Flux.fromIterable(flux1.toIterable());

        System.out.println(flux2.blockFirst());


        Integer[] numbers = new Integer[] {1, 2, 3, 4};

        // create from array
        Flux<Integer> arrayFlux = Flux.fromArray(numbers);
        arrayFlux.subscribe(System.out::println);


        // complete
        Flux emptyFlux = Flux.empty();
        emptyFlux.subscribe(System.out::println);


        // error
        Flux errorFlux = Flux.error(new Exception("test"));


        // none msg
        Flux neverFlux = Flux.never();
        neverFlux.subscribe(System.out::println);


        Flux<Integer> rangesFlux = Flux.range(10, 4);
        rangesFlux.subscribe(System.out::println);

        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);

    }


    @Test
    public void fluxGenerate() {

        Flux.generate(synchronousSink -> {
            // next can invoke at most once
            synchronousSink.next("Hello");
            synchronousSink.complete();
        }).doOnNext(System.out::println);

        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;

        }).subscribe(System.out::println);

    }

}
