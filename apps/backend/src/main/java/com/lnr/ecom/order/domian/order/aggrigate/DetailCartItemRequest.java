package com.lnr.ecom.order.domian.order.aggrigate;

import com.lnr.ecom.product.domain.vo.PublicId;
import org.jilt.Builder;

@Builder
public record DetailCartItemRequest(PublicId publicId, long quentity) {
}
