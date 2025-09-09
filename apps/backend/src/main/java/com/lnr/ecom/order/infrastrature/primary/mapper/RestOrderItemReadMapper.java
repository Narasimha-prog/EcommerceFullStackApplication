package com.lnr.ecom.order.infrastrature.primary.mapper;

import com.lnr.ecom.order.domian.order.aggrigate.OrderedProduct;
import com.lnr.ecom.order.infrastrature.primary.order.RestOrderItemRead;
import com.lnr.ecom.order.infrastrature.primary.order.RestOrderItemReadBuilder;

import java.util.List;

public class RestOrderItemReadMapper {

  private RestOrderItemReadMapper(){}



  public  static  RestOrderItemRead toRest(OrderedProduct orderedProduct){

    return RestOrderItemReadBuilder.restOrderItemRead()
      .name(orderedProduct.getProductName().value())
      .price(orderedProduct.getProductPrice().value())
      .quantity(orderedProduct.getOrderQuentity().value())
      .build();
  }


  public static List<RestOrderItemRead> toRestList(List<OrderedProduct> orderedProductList){

    return orderedProductList.stream().map(RestOrderItemReadMapper::toRest).toList();
  }
}
