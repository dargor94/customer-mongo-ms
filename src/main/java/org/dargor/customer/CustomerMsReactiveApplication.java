package org.dargor.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories("org.dargor.customer.core.repository")
@EntityScan("org.dargor.customer.core.entity")
@ComponentScan({
        "org.dargor.customer.app.config",
        "org.dargor.customer.app.messaging",
        "org.dargor.customer.app.routing",
        "org.dargor.customer.app.service",
        "org.dargor.customer.core.repository"
})
public class CustomerMsReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerMsReactiveApplication.class, args);
    }

}
