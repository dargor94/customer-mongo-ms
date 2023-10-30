package org.dargor.customer.app.routing;

import lombok.AllArgsConstructor;
import org.dargor.customer.app.dto.CustomerResponseDto;
import org.dargor.customer.app.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ProductHandler {

    private final ProductService productService;

    public Mono<ServerResponse> saveProducts(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.saveProducts(serverRequest), CustomerResponseDto.class);
    }

    public Mono<ServerResponse> saveProductsKafka(ServerRequest serverRequest) {
        return productService.saveProductsKafka(serverRequest)
                .then(ServerResponse.noContent().build());
    }

}
