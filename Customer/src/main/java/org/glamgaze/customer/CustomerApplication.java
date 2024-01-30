package org.glamgaze.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.glamgaze.library.*", "org.glamgaze.customer.*"})
@EnableJpaRepositories(value = "org.glamgaze.library.repository")
@EntityScan(value = "org.glamgaze.library.model")
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
