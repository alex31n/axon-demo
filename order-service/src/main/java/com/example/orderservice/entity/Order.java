package com.example.orderservice.entity;

import com.example.orderservice.enumeration.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
  @Id
  private String id;
  private String productId;
  private int quantity;
  private String userId;
  private OrderStatus status;

}
