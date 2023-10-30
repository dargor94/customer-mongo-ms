package org.dargor.customer.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private String id;
    private String name;
    private String lastname;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ProductResponseDto> products;

}
