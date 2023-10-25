package org.dargor.customer.app.service;

import org.dargor.customer.app.dto.CustomerRequestDto;
import org.dargor.customer.app.dto.CustomerResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<CustomerResponseDto> saveCustomer(CustomerRequestDto customerRequestDto);

    Flux<CustomerResponseDto> getAllCustomers();

}
