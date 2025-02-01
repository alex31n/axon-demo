package com.example.orderservice.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateOrderCommand {
  @TargetAggregateIdentifier
  private String orderId;
  private String product;
  private int quantity;
  private String customerId;

  public CreateOrderCommand(String orderId, String product, int quantity, String customerId) {
    this.orderId = orderId;
    this.product = product;
    this.quantity = quantity;
    this.customerId = customerId;
  }

}