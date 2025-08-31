package com.lnr.ecom.order.domian.order.vo;

import com.lnr.ecom.shared.error.domain.Assert;

public record OrderPrice(double value) {
  public OrderPrice {
    Assert.field("value",value).strictlyPositive();
  }
}
