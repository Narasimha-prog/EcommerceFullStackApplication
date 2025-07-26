package com.lnr.ecom.order.domian.user.vo;

import com.lnr.ecom.shared.error.domain.Assert;

public record UserLastName(String userLastName) {

  public UserLastName{
    Assert.field("UserLastName",userLastName).maxLength(255);
  }


}
