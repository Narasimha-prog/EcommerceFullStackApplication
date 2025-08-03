package com.lnr.ecom.product.vo;

import com.lnr.ecom.shared.error.domain.Assert;

import java.util.UUID;

public record PublicId(UUID value) {
  public PublicId {
    Assert.notNull("value",value);
  }
}
