package com.lnr.ecom.order.domian.user.vo;

import com.lnr.ecom.shared.error.domain.Assert;
import org.jilt.Builder;

@Builder
public record UserAddressToUpdate(UserPublicId userPublicId,UserAddress userAddress) {

  public UserAddressToUpdate {
    Assert.notNull("UserPublicId",userPublicId);
    Assert.notNull("UserAddress",userAddress);
  }
}
