package com.lnr.ecom.product.domain.vo;

import com.lnr.ecom.shared.error.domain.Assert;

public record ProductDescription(String value) {
  public ProductDescription {
    Assert.field("value",value).notNull().minLength(10);
  }
}
