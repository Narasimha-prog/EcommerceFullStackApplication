package com.lnr.ecom.order.infrastrature.primary.order;

import com.lnr.ecom.product.infrastrature.primary.RestPitchure;
import com.lnr.ecom.shared.error.domain.Assert;
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

  public RestProductCart {
    Assert.notNull("name",name);

    Assert.notNull("brand",brand);

    Assert.field("quantity",quantity).positive();

    Assert.field("price",price).positive();

    Assert.notNull("publicId",publicId);

    Assert.notNull("picture",picture);
  }
}
