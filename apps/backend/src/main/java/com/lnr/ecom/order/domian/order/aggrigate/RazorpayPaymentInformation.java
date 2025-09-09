package com.lnr.ecom.order.domian.order.aggrigate;

import com.lnr.ecom.order.domian.order.vo.OrderStatus;
import com.lnr.ecom.order.domian.order.vo.RazorpayPaymentId;
import com.lnr.ecom.order.domian.user.vo.UserAddressToUpdate;
import org.jilt.Builder;

import java.util.List;

@Builder
public record RazorpayPaymentInformation(RazorpayPaymentId razorpayId,
                                         OrderStatus status,
                                         List<OrderProductQuantity> productQuantityList
){



}
