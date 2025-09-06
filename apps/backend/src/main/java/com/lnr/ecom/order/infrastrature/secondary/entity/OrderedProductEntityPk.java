package com.lnr.ecom.order.infrastrature.secondary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.jilt.Builder;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedProductEntityPk implements Serializable {

  @ManyToOne
  @JoinColumn(name ="fk_order",nullable = false)
  private OrderEntity order;

  @Column(name = "fk_product" ,nullable = false)
  private UUID productPublicId;

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof OrderedProductEntityPk that)) return false;
    return Objects.equals(productPublicId, that.productPublicId);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(productPublicId);
  }
}
