package org.dargor.customer.core.entity;

import lombok.*;

import java.util.Currency;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String denomination;
    private Currency unitPrice;

}
