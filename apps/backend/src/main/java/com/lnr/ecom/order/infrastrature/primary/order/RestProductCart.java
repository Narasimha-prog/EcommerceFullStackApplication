package com.lnr.ecom.order.infrastrature.primary.order;

import com.lnr.ecom.product.infrastrature.primary.RestPitchure;
import org.jilt.Builder;

import java.util.UUID;

@Builder
public record RestProductCart(
  String name,
  String brand,
  double price,
  int quantity,
  RestPitchure picture,
  UUID publicId

) {
}
