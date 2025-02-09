package com.example.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductUpdateRequestDto {
  private String name;
  private int quantity;
  private double price;

}
