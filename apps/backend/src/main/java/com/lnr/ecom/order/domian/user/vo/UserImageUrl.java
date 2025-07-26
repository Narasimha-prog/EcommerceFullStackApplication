package com.lnr.ecom.order.domian.user.vo;

import com.lnr.ecom.shared.error.domain.Assert;

public record UserImageUrl(String imageUrl) {

public UserImageUrl {
  Assert.field("UserImageUrl",imageUrl).maxLength(1000);
}

}
