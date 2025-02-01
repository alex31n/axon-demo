package com.example.orderservice.api;

import com.example.orderservice.command.CreateOrderCommand;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderCommandController {

  private final CommandGateway commandGateway;


  @PostMapping
  public CompletableFuture<String> createOrder(@RequestBody CreateOrderCommand command) {
    String orderId = UUID.randomUUID().toString();
    command = new CreateOrderCommand(orderId, command.getProduct(), command.getQuantity(), command.getCustomerId());
    return commandGateway.send(command);
  }
}