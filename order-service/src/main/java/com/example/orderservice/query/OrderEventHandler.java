package com.example.orderservice.query;

import com.example.orderservice.event.OrderCreatedEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

  private final ConcurrentMap<String, Order> orderStore = new ConcurrentHashMap<>();

  @EventHandler
  public void on(OrderCreatedEvent event) {
    Order order = Order.builder()
        .orderId(event.getOrderId())
        .productId(event.getProductId())
        .quantity(event.getQuantity())
        .userId(event.getUserId())
        .status(OrderStatus.CREATED)
        .build();
    orderStore.put(event.getOrderId(), order);
  }

  @QueryHandler
  public List<Order> handle(FindAllOrdersQuery query) {
    return new ArrayList<>(orderStore.values());
  }
}
