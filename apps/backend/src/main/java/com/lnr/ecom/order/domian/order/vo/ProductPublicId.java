package com.lnr.ecom.order.domian.order.vo;

import com.lnr.ecom.shared.error.domain.Assert;

import java.util.UUID;

public record ProductPublicId(UUID value) {
  public ProductPublicId {
    Assert.notNull("value",value);
  }
}
