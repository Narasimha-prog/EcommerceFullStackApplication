package com.lnr.ecom.order.infrastrature.primary.mapper;

import com.lnr.ecom.order.domian.order.vo.RazorpayPaymentId;
import com.lnr.ecom.order.infrastrature.primary.order.RestRazorPayId;
import com.lnr.ecom.order.infrastrature.primary.order.RestRazorPayIdBuilder;
import com.lnr.ecom.order.infrastrature.primary.order.RestRazorPaySessionBuilder;

public class RestRazorPayMapper {

  private RestRazorPayMapper(){}


  public static RestRazorPayId toDomain(RazorpayPaymentId sessionId){

    return RestRazorPayIdBuilder.restRazorPayId()
     .id(sessionId.value()).build();

  }
}
