package org.dargor.customer.core.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@Document("customers")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    private UUID id;
    private String name;
    private String lastname;
    private List<Product> products;

}
