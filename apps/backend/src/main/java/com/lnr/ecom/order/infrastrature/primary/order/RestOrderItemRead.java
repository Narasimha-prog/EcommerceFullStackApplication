package com.lnr.ecom.order.infrastrature.primary.order;

import com.lnr.ecom.shared.error.domain.Assert;
import org.jilt.Builder;

@Builder
public record RestOrderItemRead(long quantity,double price, String name) {

  public RestOrderItemRead {
    Assert.notNull("name",name);
    Assert.field("quantity",quantity).positive();
    Assert.field("price",price).positive();

  }
}
