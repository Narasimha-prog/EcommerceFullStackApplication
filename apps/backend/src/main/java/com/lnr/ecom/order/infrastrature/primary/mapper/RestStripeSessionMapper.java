package com.lnr.ecom.order.infrastrature.primary.mapper;

import com.lnr.ecom.order.domian.order.vo.StripeSessionId;
import com.lnr.ecom.order.infrastrature.primary.order.RestStripeSession;
import com.lnr.ecom.order.infrastrature.primary.order.RestStripeSessionBuilder;

public class RestStripeSessionMapper {

  private RestStripeSessionMapper(){}


  public static RestStripeSession toDomain(StripeSessionId sessionId){

    return RestStripeSessionBuilder.restStripeSession()
      .id(sessionId.value()).build();
  }
}
