package com.lnr.ecom.order.domian.order.vo;

import com.lnr.ecom.shared.error.domain.Assert;

public record StripeSessionId(String value) {
  public StripeSessionId {
    Assert.notNull("value",value);
  }
}
