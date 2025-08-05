package com.lnr.ecom.product.domain.vo;

import com.lnr.ecom.shared.error.domain.Assert;

public record ProductBrand(String value) {

  public ProductBrand {
    Assert.field("value",value).notNull().minLength(3);
  }
}
