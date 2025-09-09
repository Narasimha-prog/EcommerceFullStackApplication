package com.lnr.ecom.order.infrastrature.primary.mapper;

import com.lnr.ecom.order.domian.order.vo.RazorpayPaymentId;
import com.lnr.ecom.order.infrastrature.primary.order.RestRazorPaySession;
import com.lnr.ecom.order.infrastrature.primary.order.RestRazorPaySessionBuilder;

public class RestStripeSessionMapper {

  private RestStripeSessionMapper(){}


  public static RestRazorPaySession toDomain(RazorpayPaymentId sessionId){

    return RestRazorPaySessionBuilder.restRazorPaySession()
     .id(sessionId.value()).build();

  }
}
