package com.lnr.ecom.order.domian.order.mapper;

import com.lnr.ecom.order.domian.order.aggrigate.ProductCart;
import com.lnr.ecom.order.infrastrature.primary.order.RestProductCart;
import com.lnr.ecom.order.infrastrature.primary.order.RestProductCartBuilder;
import com.lnr.ecom.product.infrastrature.primary.mapper.RestPictureMapper;

import java.util.List;

public class ProductCartMapper {
  private ProductCartMapper(){}

  public static RestProductCart toDomain(ProductCart productCart){
    return RestProductCartBuilder.restProductCart()
      .name(productCart.getName().value())
      .brand(productCart.getBrand().value())
      .price(productCart.getPrice().value())
      .picture(RestPictureMapper.toRestDomain(productCart.getPicture()))
      .publicId(productCart.getPublicId().value())
      .build();
  }

  public static List<RestProductCart> toDomainList(List<ProductCart> productCarts){
  return productCarts.stream().map(ProductCartMapper::toDomain).toList();
  }
}
