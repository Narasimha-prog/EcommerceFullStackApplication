package com.lnr.ecom.order.domian.user.vo;


import com.lnr.ecom.shared.error.domain.Assert;

public record UserEmail(String email) {
  public UserEmail{
    Assert.field("email",email).maxLength(225);
  }
}
