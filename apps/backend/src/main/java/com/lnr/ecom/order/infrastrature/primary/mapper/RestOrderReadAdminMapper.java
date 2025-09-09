package com.lnr.ecom.order.infrastrature.primary.mapper;

import com.lnr.ecom.order.domian.order.aggrigate.Order;
import com.lnr.ecom.order.infrastrature.primary.order.RestOrderItemRead;
import com.lnr.ecom.order.infrastrature.primary.order.RestOrderReadAdmin;
import com.lnr.ecom.order.infrastrature.primary.order.RestOrderReadAdminBuilder;

public class RestOrderReadAdminMapper {


  private RestOrderReadAdminMapper(){}


  public static RestOrderReadAdmin toRest(Order order){

StringBuilder address=new StringBuilder();

if(order.getUser().getAddress() !=null){

  address.append(order.getUser().getAddress().street());
  address.append(", ");
  address.append(order.getUser().getAddress().city());
  address.append(", ");
  address.append(order.getUser().getAddress().zipCode());
  address.append(", ");
  address.append(order.getUser().getAddress().cuntry());
}

    return RestOrderReadAdminBuilder.restOrderReadAdmin()
      .publicId(order.getPublicId().value())
      .status(order.getOrderStatus())
      .orderedItems(RestOrderItemReadMapper.toRestList(order.getOrderedProductList()))
      .address(address.toString())
      .email(order.getUser().getEmail().email())
      .build();
  }
}
