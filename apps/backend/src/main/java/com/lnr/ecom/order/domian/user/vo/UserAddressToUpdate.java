package com.lnr.ecom.order.domian.user.vo;

import org.jilt.Builder;

@Builder
public record UserAddressToUpdate(PublicId userPublicId,UserAddress userAddress) {
}
