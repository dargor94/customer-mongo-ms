package org.dargor.customer.app.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.dargor.customer.app.dto.CustomerRequestDto;
import org.dargor.customer.app.dto.CustomerResponseDto;
import org.dargor.customer.core.repository.CustomerRepository;
import org.dargor.customer.core.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Mono<CustomerResponseDto> saveCustomer(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CustomerRequestDto.class)
                .map(MapperUtil::toCustomer)
                .flatMap(customerRepository::save)
                .map(MapperUtil::toCustomerResponse);
    }

    @Override
    public Flux<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll().map(MapperUtil::toCustomerResponse);
    }


}
