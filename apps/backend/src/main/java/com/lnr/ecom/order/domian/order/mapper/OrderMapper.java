package com.lnr.ecom.order.domian.order.mapper;


import com.lnr.ecom.order.domian.order.aggrigate.ProductCart;
import com.lnr.ecom.order.domian.order.aggrigate.ProductCartBuilder;
import com.lnr.ecom.product.domain.aggregate.Product;
import jakarta.persistence.EntityNotFoundException;



public class OrderMapper {

  private OrderMapper(){

  }

  public static ProductCart toOrder(Product product){
    ProductCart productCart= ProductCartBuilder.productCart()
      .name(product.getProductName())
      .brand(product.getProductBrand())
      .price(product.getProductPrice())
      .picture(product.getPictures().stream().findFirst().orElseThrow(()->new EntityNotFoundException("Image is not found")))
      .publicId(product.getPublicId())
      .build();

    return productCart;
  }
}
