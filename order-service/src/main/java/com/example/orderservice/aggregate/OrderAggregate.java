package com.example.orderservice.aggregate;

import com.example.orderservice.command.CreateOrderCommand;
import com.example.orderservice.event.OrderCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class OrderAggregate {

  @AggregateIdentifier
  private String orderId;
  private String productId;
  private int quantity;
  private String userId;

  @CommandHandler
  public OrderAggregate(CreateOrderCommand command) {
    AggregateLifecycle.apply(OrderCreatedEvent.builder()
        .orderId(command.getOrderId())
        .productId(command.getProductId())
        .quantity(command.getQuantity())
        .userId(command.getUserId())
        .build());
  }

  @EventSourcingHandler
  public void on(OrderCreatedEvent event) {
    this.orderId = event.getOrderId();
    this.productId = event.getProductId();
    this.quantity = event.getQuantity();
    this.userId = event.getUserId();
  }
}
