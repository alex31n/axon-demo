package com.example.orderservice.api;

import com.example.orderservice.command.CreateOrderCommand;
import com.example.orderservice.dto.CreateOrderDto;
import com.example.orderservice.query.FindAllOrdersQuery;
import com.example.orderservice.query.FindOrderQuery;
import com.example.orderservice.query.Order;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderCommandController {

  private final CommandGateway commandGateway;

  private final QueryGateway queryGateway;

  @PostMapping
  public CompletableFuture<String> createOrder(@RequestBody CreateOrderDto dto) {
    String orderId = UUID.randomUUID().toString();
    CreateOrderCommand command = CreateOrderCommand.builder()
        .orderId(orderId)
        .productId(dto.getProductId())
        .quantity(dto.getQuantity())
        .userId(dto.getUserId())
        .build();
    return commandGateway.send(command);
  }

  @GetMapping("/{orderId}")
  public CompletableFuture<Order> getOrderById(@PathVariable String orderId) {
    return queryGateway.query(new FindOrderQuery(orderId), Order.class);
  }

  @GetMapping
  public CompletableFuture<List<Order>> getAllOrders() {
    return queryGateway.query(new FindAllOrdersQuery(),
        ResponseTypes.multipleInstancesOf(Order.class));
  }
}