package com.springbootreactor.reactorcourse;

import com.springbootreactor.reactorcourse.models.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class ReactorCourseApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ReactorCourseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        iterableAndFirstSteps();
        iterableAndFirstStepsFlatMap();
    }

    private static void iterableAndFirstSteps() {
        List<String> usuarios = new ArrayList<>();
        usuarios.add("Felipe");
        usuarios.add("Por");
        usuarios.add("Fafi");

        Flux<String> flux = Flux.fromIterable(usuarios);

        Flux<UserModel> users = flux.filter(s -> s.startsWith("F"))
                .doOnNext(e -> {
                    if (e == null) {
                        throw new RuntimeException("Empty String");
                    }
                    System.out.println(e);
                }).map(s -> {
                    return new UserModel(s.toUpperCase());
                });
        flux.subscribe(element -> log.info(element.toString()), error -> log.error("Error is: " + error), new Runnable() {
            @Override
            public void run() {
                log.info("Completed");
            }
        });

        users.subscribe(element -> log.info(element.toString()), error -> log.error("Error is: " + error), new Runnable() {
            @Override
            public void run() {
                log.info("Completed");
            }
        });
    }
    private static void iterableAndFirstStepsFlatMap() {
        List<String> usuarios = new ArrayList<>();
        usuarios.add("Felipe Flat Map");
        usuarios.add("Por Flat Map");
        usuarios.add("Fafi Flat Map");

        Flux<String> flux = Flux.fromIterable(usuarios);

        Flux<UserModel> users = flux.filter(s -> s.startsWith("F"))
                .map(s -> new UserModel(s.toUpperCase()));
        flux.subscribe(log::info);
        users.subscribe(element -> log.info(element.toString()));
    }
}
