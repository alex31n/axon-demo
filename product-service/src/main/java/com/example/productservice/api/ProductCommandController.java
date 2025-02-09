package com.example.productservice.api;

import com.example.productservice.command.CreateProductCommand;
import com.example.productservice.command.DeleteProductCommand;
import com.example.productservice.command.UpdateProductCommand;
import com.example.productservice.dto.ProductCreateRequestDto;
import com.example.productservice.dto.ProductUpdateRequestDto;
import com.example.productservice.entity.Product;
import com.example.productservice.query.FindAllProductsQuery;
import com.example.productservice.query.FindProductQuery;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductCommandController {

  private final CommandGateway commandGateway;

  private final QueryGateway queryGateway;

  @PostMapping
  public CompletableFuture<String> create(@RequestBody ProductCreateRequestDto dto) {
    String id = UUID.randomUUID().toString();
    CreateProductCommand command = CreateProductCommand.builder()
        .productId(id)
        .name(dto.getName())
        .price(dto.getPrice())
        .quantity(dto.getQuantity())
        .build();
    return commandGateway.send(command);
  }

  @PatchMapping("/{id}")
  public CompletableFuture<String> update(@PathVariable String id, @RequestBody ProductUpdateRequestDto dto) {
    var command = UpdateProductCommand.builder()
        .productId(id)
        .name(dto.getName())
        .price(dto.getPrice())
        .quantity(dto.getQuantity())
        .build();
    return commandGateway.send(command);
  }

  @DeleteMapping("/{id}")
  public CompletableFuture<Product> delete(@PathVariable String id) {
    return commandGateway.send(new DeleteProductCommand(id));
  }

  @GetMapping("/{id}")
  public CompletableFuture<Product> getById(@PathVariable String id) {
    return queryGateway.query(new FindProductQuery(id), Product.class);
  }

  @GetMapping
  public CompletableFuture<List<Product>> getAll() {
    return queryGateway.query(new FindAllProductsQuery(),
        ResponseTypes.multipleInstancesOf(Product.class));
  }
}