package org.dargor.customer.app.messagging.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dargor.customer.app.dto.ProductRequestDtoWrapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static org.dargor.customer.app.messagging.Constants.PRODUCTS_TOPIC;

@Slf4j
@Component
@AllArgsConstructor
public class ProductProducer {

    private final KafkaTemplate<String, ProductRequestDtoWrapper> producer;

    public void saveProducts(ProductRequestDtoWrapper requestDto) {
        producer.send(PRODUCTS_TOPIC, requestDto);
    }

}
