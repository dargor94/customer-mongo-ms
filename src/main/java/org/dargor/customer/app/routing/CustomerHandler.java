package org.dargor.customer.app.routing;

import lombok.AllArgsConstructor;
import org.dargor.customer.app.dto.CustomerResponseDto;
import org.dargor.customer.app.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CustomerHandler {

    private final CustomerService customerService;

    public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(customerService.saveCustomer(serverRequest), CustomerResponseDto.class);
    }

    public Mono<ServerResponse> getAllCustomers() {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(customerService.getAllCustomers(), CustomerResponseDto.class);
    }

}
