package com.mcddhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mcddhub"})
public class HowSpringBoot3Application {

    public static void main(String[] args) {
        SpringApplication.run(HowSpringBoot3Application.class, args);
    }

}
