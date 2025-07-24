package com.lnr.ecom.order.domian.user.vo;

import com.lnr.ecom.shared.error.domain.Assert;
import org.jilt.Builder;

@Builder
public record UserAddress(String street, String city, String  zipCode, String cuntry) {
  public UserAddress{

    Assert.field("street",street).notNull();
    Assert.field("zipCode",zipCode).notNull();
    Assert.field("city",city).notNull();
    Assert.field("cuntry",cuntry).notNull();


  }
}
