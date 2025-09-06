package com.lnr.ecom.order.domian.order.mapper;

import com.lnr.ecom.order.domian.order.aggrigate.DetailCartResponse;
import com.lnr.ecom.order.infrastrature.primary.order.RestDetailCartResponse;
import com.lnr.ecom.order.infrastrature.primary.order.RestDetailCartResponseBuilder;

public class DetailCartResponseMapper {

  private DetailCartResponseMapper(){}

  public static RestDetailCartResponse toDomain(DetailCartResponse cartResponse){

    return RestDetailCartResponseBuilder.restDetailCartResponse()
      .products(cartResponse.getProducts().stream().map(ProductCartMapper::toDomain).toList())
      .build();
  }
}
