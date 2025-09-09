package com.lnr.ecom.order.infrastrature.primary.mapper;

import com.lnr.ecom.order.domian.order.aggrigate.Order;
import com.lnr.ecom.order.infrastrature.primary.order.ResOrderRead;
import com.lnr.ecom.order.infrastrature.primary.order.ResOrderReadBuilder;

public class RestOrderReadMapper {

  private RestOrderReadMapper(){}


  public static ResOrderRead toRest(Order order){

    return ResOrderReadBuilder.resOrderRead()
      .publicId(order.getPublicId().value())
      .status(order.getOrderStatus())
      .orderedItems(RestOrderItemReadMapper.toRestList(order.getOrderedProductList()))
      .build();
  }
}
