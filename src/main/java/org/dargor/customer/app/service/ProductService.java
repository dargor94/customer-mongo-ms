package org.dargor.customer.app.service;

import org.dargor.customer.app.dto.CustomerResponseDto;
import org.dargor.customer.app.dto.ProductRequestDtoWrapper;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<CustomerResponseDto> saveProducts(ServerRequest serverRequest);

    void saveProducts(Flux<ProductRequestDtoWrapper> productRequest);

    Mono<Void> saveProductsKafka(ServerRequest serverRequest);

}
