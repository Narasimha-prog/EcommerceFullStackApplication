package com.lnr.ecom.order.infrastrature.secondary.mapper;

import com.lnr.ecom.order.domian.order.aggrigate.Order;
import com.lnr.ecom.order.domian.order.aggrigate.OrderBuilder;
import com.lnr.ecom.order.domian.order.aggrigate.OrderedProduct;
import com.lnr.ecom.order.domian.user.mapper.UserMapper;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderEntity;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderEntityBuilder;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderedProductEntity;
import com.lnr.ecom.product.domain.vo.PublicId;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderEntityMapper {
  private OrderEntityMapper(){}


  public static OrderEntity toEntity(Order order){

    Set<OrderedProductEntity> orderedProductEntity = order.getOrderedProductList().stream().map(OrderedProductEntityMapper::toEntity).collect(Collectors.toSet());

    return OrderEntityBuilder.orderEntity()
      .publicId(order.getPublicId().value())
      .status(order.getOrderStatus())
      .razorpayId(order.getRazorpayId())
      .orderedProducts(orderedProductEntity)
      .user(UserMapper.toEntity(order.getUser()))
     .build();
  }
public static  Order toDomain(OrderEntity orderEntity){

  Set<OrderedProduct> orderedProducts = orderEntity.getOrderedProducts().stream().map(OrderedProductEntityMapper::toDomain).collect(Collectors.toSet());

  return OrderBuilder.order()
    .user(UserMapper.toDomain(orderEntity.getUser()))
    .publicId(new PublicId(orderEntity.getPublicId()))
    .orderStatus(orderEntity.getStatus())
    .razorpayId(orderEntity.getRazorpayId())
    .orderedProductList(orderedProducts.stream().toList())
    .build();
}


}
