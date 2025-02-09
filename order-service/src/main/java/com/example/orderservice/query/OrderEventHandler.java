package com.example.orderservice.query;

import com.example.orderservice.entity.Order;
import com.example.orderservice.enumeration.OrderStatus;
import com.example.orderservice.event.OrderCreatedEvent;
import com.example.orderservice.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventHandler {

  private final OrderRepository orderRepository;

  @EventHandler
  public void on(OrderCreatedEvent event) {
    Order order = Order.builder()
        .id(event.getOrderId())
        .productId(event.getProductId())
        .quantity(event.getQuantity())
        .userId(event.getUserId())
        .status(OrderStatus.CREATED)
        .build();
    orderRepository.save(order);
  }

  @QueryHandler
  public List<Order> handle(FindAllOrdersQuery query) {
    return new ArrayList<>(orderRepository.findAll());
  }

  /*@QueryHandler
  public Order handle(FindOrderQuery query) {
    return orderRepository.findById(query.getOrderId());
  }*/


}
