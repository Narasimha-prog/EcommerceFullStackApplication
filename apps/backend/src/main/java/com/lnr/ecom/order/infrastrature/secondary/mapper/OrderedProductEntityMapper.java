package com.lnr.ecom.order.infrastrature.secondary.mapper;

import com.lnr.ecom.order.domian.order.aggrigate.OrderedProduct;
import com.lnr.ecom.order.domian.order.aggrigate.OrderedProductBuilder;
import com.lnr.ecom.order.domian.order.vo.OrderPrice;
import com.lnr.ecom.order.domian.order.vo.OrderQuentity;
import com.lnr.ecom.order.domian.order.vo.ProductPublicId;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderedProductEntity;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderedProductEntityBuilder;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderedProductEntityPk;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderedProductEntityPkBuilder;
import com.lnr.ecom.product.domain.vo.ProductName;

import java.util.List;

public class OrderedProductEntityMapper {

  private OrderedProductEntityMapper(){}

   public static OrderedProductEntity toEntity(OrderedProduct orderedProduct){

     OrderedProductEntityPk compositeId = OrderedProductEntityPkBuilder.orderedProductEntityPk()
       .productPublicId(orderedProduct.getProductPublicId().value())
       .build();
     return OrderedProductEntityBuilder.orderedProductEntity()
       .productName(orderedProduct.getProductName().value())
       .quantity(orderedProduct.getOrderQuentity().value())
       .price(orderedProduct.getProductPrice().value())
       .id(compositeId)
       .build();

   }

   public static OrderedProduct toDomain(OrderedProductEntity orderedProductEntity){
    return OrderedProductBuilder.orderedProduct()
      .productName(new ProductName( orderedProductEntity.getProductName()))
      .productPrice(new OrderPrice(orderedProductEntity.getPrice()))
      .orderQuentity(new OrderQuentity(orderedProductEntity.getQuantity()))
      .productPublicId(new ProductPublicId(orderedProductEntity.getId().getProductPublicId()))
      .build();
   }

   public static List<OrderedProductEntity> toEntityList(List<OrderedProduct> orderedProductList){
    return orderedProductList.stream().map(OrderedProductEntityMapper::toEntity).toList();
   }
}
