package com.example.productservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateProductCommand {

  @TargetAggregateIdentifier
  private String productId;
  private String name;
  private int quantity;
  private double price;

}