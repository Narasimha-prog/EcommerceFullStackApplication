package com.lnr.ecom.order.infrastrature.primary.order;

import com.lnr.ecom.shared.error.domain.Assert;
import org.jilt.Builder;

import java.util.List;
@Builder
public record RestDetailCartResponse(List<RestProductCart> products) {
  public RestDetailCartResponse {
    Assert.notNull("productCart",products);
  }
}
