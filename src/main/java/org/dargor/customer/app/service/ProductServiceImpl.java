package org.dargor.customer.app.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dargor.customer.app.dto.CustomerResponseDto;
import org.dargor.customer.app.dto.ProductRequestDtoWrapper;
import org.dargor.customer.app.messagging.producer.ProductProducer;
import org.dargor.customer.core.repository.CustomerRepository;
import org.dargor.customer.core.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CustomerRepository customerRepository;
    private final ProductProducer productProducer;

    @Override
    public Mono<CustomerResponseDto> saveProducts(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ProductRequestDtoWrapper.class)
                .flatMap(request -> customerRepository.findById(request.getCustomerId())
                        .map(customer -> {
                            var products = MapperUtil.toProducts(request.getProducts());
                            Optional.ofNullable(customer.getProducts())
                                    .ifPresentOrElse(products1 -> customer
                                            .getProducts().addAll(products), () -> customer
                                            .setProducts(products));
                            return customer;
                        }))
                .flatMap(customerRepository::save)
                .map(MapperUtil::toCustomerResponse);
    }

    @Override
    public Mono<Void> saveProductsKafka(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ProductRequestDtoWrapper.class)
                .flatMap(request -> {
                    productProducer.saveProducts(request);
                    return Mono.empty();
                });
    }
}