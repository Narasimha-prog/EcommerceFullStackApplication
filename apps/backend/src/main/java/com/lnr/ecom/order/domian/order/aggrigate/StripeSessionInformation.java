package com.lnr.ecom.order.domian.order.aggrigate;

import com.lnr.ecom.order.domian.order.vo.StripeSessionId;
import com.lnr.ecom.order.domian.user.vo.UserAddressToUpdate;

public record StripeSessionInformation(StripeSessionId stripeSessionId, UserAddressToUpdate userAddressToUpdate {
}
