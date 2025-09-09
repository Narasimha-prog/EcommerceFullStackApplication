package com.lnr.ecom.order.domian.user.vo;

import com.lnr.ecom.shared.error.domain.Assert;

import java.util.UUID;

public record UserPublicId(UUID value) {
  public UserPublicId {
    Assert.notNull("UserPublicId",value);
  }
}
