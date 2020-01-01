package com.supermap.testMultiModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootApplication
@EnableCaching
@EmbeddedKafka(count = 4,ports = {9092,9093,9094,9095})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

//public class Application extends SpringBootServletInitializer {
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(Application.class);
//    }
//}