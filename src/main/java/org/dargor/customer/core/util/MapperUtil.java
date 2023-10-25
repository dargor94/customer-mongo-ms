package org.dargor.customer.core.util;

import org.dargor.customer.app.dto.CustomerRequestDto;
import org.dargor.customer.app.dto.CustomerResponseDto;
import org.dargor.customer.core.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {


    public static Customer toCustomer(CustomerRequestDto customerRequestDto) {
        return Customer.builder()
                .name(customerRequestDto.getFirstName())
                .lastname(customerRequestDto.getLastName())
                .build();
    }

    public static CustomerResponseDto toCustomerResponse(Customer customer) {
        return CustomerResponseDto.builder().build();
    }
}
