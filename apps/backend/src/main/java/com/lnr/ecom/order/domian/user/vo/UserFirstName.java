package com.lnr.ecom.order.domian.user.vo;

import com.lnr.ecom.shared.error.domain.Assert;

public record UserFirstName(String userFirstName){

  public UserFirstName{
    Assert.field("UserFirstName",userFirstName).maxLength(255);
  }
}
