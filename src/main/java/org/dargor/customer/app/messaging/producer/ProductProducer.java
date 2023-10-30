package org.dargor.customer.app.messaging.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dargor.customer.app.dto.ProductRequestDtoWrapper;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
public class ProductProducer {

    private static final String PRODUCTS_TOPIC = "products";
    private final ReactiveKafkaProducerTemplate<String, ProductRequestDtoWrapper> kafkaProducer;

    public Mono<Void> saveProducts(ProductRequestDtoWrapper requestDto) {
        return kafkaProducer.send(PRODUCTS_TOPIC, requestDto).then();
    }

}
