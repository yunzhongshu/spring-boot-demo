package com.zmdj.demo.explore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

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
    public void flux() {

        Flux<String> flux1 = Flux.just("first", "second", "third");

        assertTrue(flux1.blockFirst().equals("first"));

        assertTrue(flux1.blockLast().equals("third"));

        Flux<String> flux2 = Flux.fromIterable(flux1.toIterable());

        System.out.println(flux2.blockFirst());
    }

}
