package com.example.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderDto {
  private String orderId;
  private String productId;
  private int quantity;
  private String userId;
}
