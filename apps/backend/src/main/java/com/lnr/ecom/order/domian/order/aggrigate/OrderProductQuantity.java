package com.lnr.ecom.order.domian.order.aggrigate;

import com.lnr.ecom.order.domian.order.vo.OrderQuentity;
import com.lnr.ecom.order.domian.order.vo.ProductPublicId;
import org.jilt.Builder;

@Builder
public record OrderProductQuantity(OrderQuentity orderQuentity, ProductPublicId productPublicId) {
}
