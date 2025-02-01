package com.example.orderservice.aggregate;

import com.example.orderservice.command.CreateOrderCommand;
import com.example.orderservice.event.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class OrderAggregate {

  @AggregateIdentifier
  private String orderId;
  private String product;
  private int quantity;
  private String customerId;

  public OrderAggregate() {
  }

  @CommandHandler
  public OrderAggregate(CreateOrderCommand command) {
    AggregateLifecycle.apply(new OrderCreatedEvent(
        command.getOrderId(),
        command.getProduct(),
        command.getQuantity(),
        command.getCustomerId()
    ));
  }

  @EventSourcingHandler
  public void on(OrderCreatedEvent event) {
    this.orderId = event.getOrderId();
    this.product = event.getProduct();
    this.quantity = event.getQuantity();
    this.customerId = event.getCustomerId();
  }
}
