package org.dargor.customer.app.messaging.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.dargor.customer.app.dto.ProductRequestDtoWrapper;
import org.dargor.customer.app.service.ProductService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ProductConsumer {

    private static final String PRODUCTS_TOPIC = "products";
    private static final String PRODUCTS_GROUP_ID = "products_id";

    private final ProductService productService;
    private final ReactiveKafkaConsumerTemplate<String, ProductRequestDtoWrapper> kafkaConsumer;

    @KafkaListener(topics = PRODUCTS_TOPIC, groupId = PRODUCTS_GROUP_ID)
    public void consume() {
        var c = kafkaConsumer
                .receiveAutoAck()
                .map(ConsumerRecord::value);
        productService.saveProducts(c);

    }

}
