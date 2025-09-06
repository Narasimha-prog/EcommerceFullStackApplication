package com.lnr.ecom.order.infrastrature.secondary.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.jilt.Builder;

import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordered_product")
public class OrderedProductEntity {

  @EmbeddedId
  private OrderedProductEntityPk id;

  @Column(name = "price",nullable = false)
  private Double price;

@Column(name = "quantity")
  private Long quantity;

  @Column(name = "product_name",nullable = false)
  private String productName;

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof OrderedProductEntity that)) return false;
    return Objects.equals(price, that.price) && Objects.equals(quantity, that.quantity) && Objects.equals(productName, that.productName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(price, quantity, productName);
  }
}
