package com.lnr.ecom.order.infrastrature.primary.order;

import com.lnr.ecom.shared.error.domain.Assert;
import org.jilt.Builder;

import java.util.UUID;
@Builder
public record RestCartItemRequest(UUID publicId,long quantity) {

  public RestCartItemRequest {
    Assert.notNull("productPublicId",publicId);
    Assert.field("quantity",quantity).positive();

  }
}
