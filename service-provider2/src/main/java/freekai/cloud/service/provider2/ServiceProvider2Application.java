package freekai.cloud.service.provider2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceProvider2Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProvider2Application.class, args);
    }

}
