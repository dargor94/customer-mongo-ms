package org.dargor.customer.app.config;

import org.dargor.customer.app.routing.CustomerHandler;
import org.dargor.customer.app.routing.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> customerRoute(CustomerHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/customers")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        request -> handler.getAllCustomers())
                .andRoute(RequestPredicates.POST("/customers")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        handler::saveCustomer);
    }

    @Bean
    public RouterFunction<ServerResponse> productsRoute(ProductHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/products")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        handler::saveProducts)
                .andRoute(RequestPredicates.POST("/products/kafka")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        handler::saveProductsKafka);
    }

}
