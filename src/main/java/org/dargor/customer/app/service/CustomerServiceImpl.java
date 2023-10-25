package org.dargor.customer.app.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.dargor.customer.app.dto.CustomerRequestDto;
import org.dargor.customer.app.dto.CustomerResponseDto;
import org.dargor.customer.core.repository.CustomerRepository;
import org.dargor.customer.core.util.MapperUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Mono<CustomerResponseDto> saveCustomer(CustomerRequestDto customerRequestDto) {
        return customerRepository.save(MapperUtil.toCustomer(customerRequestDto))
                .map(MapperUtil::toCustomerResponse);
    }

    @Override
    public Flux<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll().map(MapperUtil::toCustomerResponse);
    }


}
