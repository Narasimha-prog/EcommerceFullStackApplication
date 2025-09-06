package com.lnr.ecom.order.infrastrature.secondary.mapper;

import com.lnr.ecom.order.domian.order.aggrigate.Order;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderEntity;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderEntityBuilder;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderedProductEntity;

import java.util.List;

public class OrderEntityMapper {
  private OrderEntityMapper(){}


  public static OrderEntity toDomain(Order order){

    List<OrderedProductEntity> orderedProductEntity = order.getOrderedProductList().stream().map(OrderedProductEntityMapper::toEntity).toList();

    return OrderEntityBuilder.orderEntity()
     .build();
  }
}
