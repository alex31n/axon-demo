package com.example.productservice.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.example.productservice.command.CreateProductCommand;
import com.example.productservice.command.DeleteProductCommand;
import com.example.productservice.command.UpdateProductCommand;
import com.example.productservice.command.UpdateProductQuantityCommand;
import com.example.productservice.event.ProductCreatedEvent;
import com.example.productservice.event.ProductDeleteEvent;
import com.example.productservice.event.ProductQuantityUpdatedEvent;
import com.example.productservice.event.ProductUpdatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {

  @AggregateIdentifier
  private String productId;
  private String name;
  private int quantity;
  private double price;

  @CommandHandler
  public ProductAggregate(CreateProductCommand command) {
    apply(ProductCreatedEvent.builder()
        .productId(command.getProductId())
        .name(command.getName())
        .quantity(command.getQuantity())
        .price(command.getPrice())
        .build());
  }

  @CommandHandler
  public void handle(UpdateProductCommand command) {
    apply(ProductUpdatedEvent.builder()
        .productId(command.getProductId())
        .name(command.getName())
        .quantity(command.getQuantity())
        .price(command.getPrice())
        .build());
  }

  @CommandHandler
  public void handle(UpdateProductQuantityCommand command) {
    apply(new ProductQuantityUpdatedEvent(command.getProductId(), command.getQuantity()));
  }

  @CommandHandler
  public void handle(DeleteProductCommand command) {
    apply(new ProductDeleteEvent(command.getProductId()));
  }


  @EventSourcingHandler
  public void on(ProductCreatedEvent event) {
    this.productId = event.getProductId();
    this.name = event.getName();
    this.quantity = event.getQuantity();
    this.price = event.getPrice();
  }

  @EventSourcingHandler
  public void on(ProductUpdatedEvent event) {
    this.productId = event.getProductId();
    this.name = event.getName();
    this.quantity = event.getQuantity();
    this.price = event.getPrice();

  }

  @EventSourcingHandler
  public void on(ProductQuantityUpdatedEvent event) {
    this.productId = event.getProductId();
    this.quantity = event.getQuantity();
  }

}
