package com.example.productservice.query;

import com.example.productservice.entity.Product;
import com.example.productservice.event.ProductCreatedEvent;
import com.example.productservice.event.ProductDeleteEvent;
import com.example.productservice.event.ProductQuantityUpdatedEvent;
import com.example.productservice.event.ProductUpdatedEvent;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class ProductEventHandler {

  private final ProductRepository productRepository;

  @EventHandler
  public void on(ProductCreatedEvent event) {
    Product product = Product.builder()
        .id(UUID.fromString(event.getProductId()))
        .name(event.getName())
        .quantity(event.getQuantity())
        .price(event.getPrice())
        .build();
    productRepository.save(product);
  }

  @EventHandler
  public void on(ProductUpdatedEvent event) {
    var product = productRepository.findById(UUID.fromString(event.getProductId())).orElseThrow(
        () -> new ProductNotFoundException("product is not found for id: " + event.getProductId())
    );

    if (StringUtils.hasText(event.getName())) {
      product.setName(event.getName());
    }

    if (event.getQuantity() > 0) {
      product.setQuantity(event.getQuantity());
    }

    if (event.getPrice() > 0) {
      product.setPrice(event.getPrice());
    }

    productRepository.save(product);

  }

  @EventHandler
  public void on(ProductQuantityUpdatedEvent event) {
    var product = productRepository.findById(UUID.fromString(event.getProductId())).orElseThrow(
        () -> new ProductNotFoundException("product is not found for id: " + event.getProductId())
    );

    if (event.getQuantity() > 0) {
      product.setQuantity(event.getQuantity());
    }

    productRepository.save(product);

  }

  @EventHandler
  public void on(ProductDeleteEvent event) {
    var product = productRepository.findById(UUID.fromString(event.getProductId())).orElseThrow(
        () -> new ProductNotFoundException("product is not found for id: " + event.getProductId())
    );
    
    productRepository.delete(product);

  }


  @QueryHandler
  public List<Product> handle(FindAllProductsQuery query) {
    return new ArrayList<>(productRepository.findAll());
  }

  @QueryHandler
  public Product handle(FindProductQuery query) {
    return productRepository.findById(UUID.fromString(query.getId())).orElseThrow(
        () -> new ProductNotFoundException("Product not found")
    );
  }

}
