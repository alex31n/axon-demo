package com.example.orderservice.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCreatedEvent {
  private String orderId;
  private String productId;
  private int quantity;
  private String userId;
}
