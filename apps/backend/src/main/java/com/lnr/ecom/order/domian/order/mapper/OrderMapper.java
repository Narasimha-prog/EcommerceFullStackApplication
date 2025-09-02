package com.lnr.ecom.order.domian.order.mapper;


import com.lnr.ecom.order.domian.order.aggrigate.ProductCart;
import com.lnr.ecom.order.domian.order.aggrigate.ProductCartBuilder;
import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.vo.PublicId;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public class OrderMapper {

  private OrderMapper(){

  }

  public static ProductCart toOrder(Product product){
    return ProductCartBuilder.productCart()
      .name(product.getProductName())
      .brand(product.getProductBrand())
      .picture(product.getPictures().stream().findFirst().orElseThrow(()->new EntityNotFoundException("Image is not found")))
      .publicId(new PublicId(UUID.randomUUID()))
      .build();
  }
}
