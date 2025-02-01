package com.example.orderservice.query;

import lombok.Data;

@Data
public class FindOrderQuery {
  private String orderId;

  public FindOrderQuery(String orderId) {
    this.orderId = orderId;
  }

  public String getOrderId() {
    return orderId;
  }
}