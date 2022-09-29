package com.gotunis.microservicerecherche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroservicerechercheApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicerechercheApplication.class, args);
    }

}
