package com.lnr.ecom.order.domian.order.vo;

import com.lnr.ecom.shared.error.domain.Assert;

public record RazorpayPaymentId(String value) {
  public RazorpayPaymentId {
    Assert.notNull("value",value);
  }
}
