package org.springboot.valid_exception.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private Long number;
    private String name;
    private int price;
    private int stock;
}
