package com.lnr.ecom.order.domian.order.vo;

import com.lnr.ecom.shared.error.domain.Assert;

public record OrderQuentity(long value) {
  public OrderQuentity {
    Assert.field("value",value).positive();
  }
}
