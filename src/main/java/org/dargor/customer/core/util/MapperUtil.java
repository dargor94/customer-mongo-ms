package org.dargor.customer.core.util;

import org.dargor.customer.app.dto.CustomerRequestDto;
import org.dargor.customer.app.dto.CustomerResponseDto;
import org.dargor.customer.app.dto.ProductRequestDto;
import org.dargor.customer.app.dto.ProductResponseDto;
import org.dargor.customer.core.entity.Customer;
import org.dargor.customer.core.entity.Product;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MapperUtil {


    public static Customer toCustomer(CustomerRequestDto customerRequestDto) {
        return Customer
                .builder()
                .firstName(customerRequestDto.getFirstName())
                .lastName(customerRequestDto.getLastName())
                .build();
    }

    public static CustomerResponseDto toCustomerResponse(Customer customer) {
        return CustomerResponseDto
                .builder()
                .id(customer.getId())
                .name(customer.getFirstName())
                .lastname(customer.getLastName())
                .products(toProductResponseDtoList(
                        Optional.ofNullable(customer.getProducts()).orElse(Collections.emptyList()))
                )
                .build();
    }

    public static List<ProductResponseDto> toProductResponseDtoList(List<Product> products) {
        return products.stream().map(MapperUtil::toProductResponse).collect(Collectors.toList());

    }

    public static ProductResponseDto toProductResponse(Product product) {
        return ProductResponseDto
                .builder()
                .denomination(product.getDenomination())
                .unitPrice(product.getUnitPrice())
                .build();
    }

    public static List<Product> toProducts(List<ProductRequestDto> products) {
        return products
                .stream()
                .map(productRequestDto -> Product.builder()
                        .denomination(productRequestDto.getDenomination())
                        .unitPrice(productRequestDto.getUnitPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
