package com.example.productservice.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductUpdatedEvent {

  private String productId;
  private String name;
  private int quantity;
  private double price;

}
