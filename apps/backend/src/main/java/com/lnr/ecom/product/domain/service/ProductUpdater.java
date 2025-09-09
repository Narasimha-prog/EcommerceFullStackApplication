package com.lnr.ecom.product.domain.service;

import com.lnr.ecom.order.domian.order.aggrigate.OrderProductQuantity;
import com.lnr.ecom.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductUpdater {

  private final ProductRepository productRepository;

  public void updateProductQuantity(List<OrderProductQuantity> orderProductQuantities){
    for(OrderProductQuantity orderProductQuantity:orderProductQuantities){
      productRepository.updateQuantity(orderProductQuantity.productPublicId(),orderProductQuantity.orderQuentity().value());

    }
  }
}
