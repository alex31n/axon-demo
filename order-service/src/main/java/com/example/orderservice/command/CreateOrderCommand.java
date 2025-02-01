package com.example.orderservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateOrderCommand {
  @TargetAggregateIdentifier
  private String orderId;
  private String productId;
  private int quantity;
  private String userId;

}