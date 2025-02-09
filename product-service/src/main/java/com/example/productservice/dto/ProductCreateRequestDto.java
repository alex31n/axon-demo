package com.example.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCreateRequestDto {
  private String productId;
  private String name;
  private int quantity;
  private double price;
}
