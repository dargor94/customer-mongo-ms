package org.dargor.customer.app.service;

import org.dargor.customer.app.dto.CustomerResponseDto;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<CustomerResponseDto> saveProducts(ServerRequest serverRequest);

    Mono<Void> saveProductsKafka(ServerRequest serverRequest);

}
