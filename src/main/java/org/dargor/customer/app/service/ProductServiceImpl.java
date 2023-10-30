package org.dargor.customer.app.service;

import lombok.AllArgsConstructor;
import org.dargor.customer.app.dto.CustomerResponseDto;
import org.dargor.customer.app.dto.ProductRequestDtoWrapper;
import org.dargor.customer.app.messaging.producer.ProductProducer;
import org.dargor.customer.core.repository.CustomerRepository;
import org.dargor.customer.core.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CustomerRepository customerRepository;
    private final ProductProducer productProducer;

    @Override
    public Mono<CustomerResponseDto> saveProducts(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ProductRequestDtoWrapper.class)
                .flatMap(request -> customerRepository.findById(request.getCustomerId())
                        .map(c -> {
                            var products = MapperUtil.toProducts(request.getProducts());
                            if (ObjectUtils.isEmpty(c.getProducts())) {
                                c.setProducts(products);
                            } else {
                                c.getProducts().addAll(products);
                            }
                            return c;
                        }))
                .flatMap(customerRepository::save)
                .map(MapperUtil::toCustomerResponse);
    }

    @Override
    public void saveProducts(ProductRequestDtoWrapper request) {
        customerRepository.findById(request.getCustomerId())
                .map(c -> {
                    var products = MapperUtil.toProducts(request.getProducts());
                    if (ObjectUtils.isEmpty(c.getProducts())) {
                        c.setProducts(products);
                    } else {
                        c.getProducts().addAll(products);
                    }
                    return c;
                }).flatMap(customerRepository::save)
                .subscribe();

    }

    @Override
    public Mono<Void> saveProductsKafka(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ProductRequestDtoWrapper.class)
                .flatMap(productProducer::saveProducts);
    }
}