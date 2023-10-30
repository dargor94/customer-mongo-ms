package org.dargor.customer.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDtoWrapper implements Serializable {

    private static final long serialVersionUID = 3105611075520438745L;

    private String customerId;
    private List<ProductRequestDto> products;

}
