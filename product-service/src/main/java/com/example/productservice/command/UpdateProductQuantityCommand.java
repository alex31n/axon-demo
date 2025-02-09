package com.example.productservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateProductQuantityCommand {

  @TargetAggregateIdentifier
  private String productId;
  private int quantity;

}