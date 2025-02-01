package com.example.orderservice.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
  private String orderId;
  private String productId;
  private int quantity;
  private String userId;
  private OrderStatus status;

}
