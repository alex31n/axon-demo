package com.example.orderservice.event;

import lombok.Data;

@Data
public class OrderCreatedEvent {
  private String orderId;
  private String product;
  private int quantity;
  private String customerId;

  public OrderCreatedEvent(String orderId, String product, int quantity, String customerId) {
    this.orderId = orderId;
    this.product = product;
    this.quantity = quantity;
    this.customerId = customerId;
  }


}
