package org.glamgaze.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.glamgaze.library.*", "org.glamgaze.admin.*"})
@EnableJpaRepositories(value = "org.glamgaze.library.repository")
@EntityScan(value = "org.glamgaze.library.model")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
