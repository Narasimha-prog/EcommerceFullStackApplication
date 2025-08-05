package com.lnr.ecom.product.domain.vo;

import com.lnr.ecom.shared.error.domain.Assert;

public record ProductName(String value) {

  public ProductName {
    Assert.notNull("value",value);
    Assert.field("value",value).notNull().minLength(3).maxLength(256);
  }
}
