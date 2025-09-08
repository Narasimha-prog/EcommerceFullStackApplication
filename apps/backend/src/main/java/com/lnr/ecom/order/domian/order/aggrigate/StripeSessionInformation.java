package com.lnr.ecom.order.domian.order.aggrigate;

import com.lnr.ecom.order.domian.order.vo.StripeSessionId;
import com.lnr.ecom.order.domian.user.vo.UserAddressToUpdate;
import org.jilt.Builder;

import java.util.List;

@Builder
public record StripeSessionInformation(StripeSessionId stripeSessionId,
                                       UserAddressToUpdate userAddressToUpdate,
                                       List<OrderProductQuantity> productQuantityList
){
}
