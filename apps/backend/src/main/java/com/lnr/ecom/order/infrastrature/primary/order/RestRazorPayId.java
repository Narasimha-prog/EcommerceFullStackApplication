package com.lnr.ecom.order.infrastrature.primary.order;


import com.lnr.ecom.shared.error.domain.Assert;
import org.jilt.Builder;

@Builder
public record RestRazorPayId (String id){


  public RestRazorPayId {
    Assert.notNull("id",id);
  }
}
