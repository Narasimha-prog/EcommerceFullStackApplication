package com.lnr.ecom.order.domian.order.service;

import com.lnr.ecom.order.domian.order.aggrigate.*;
import com.lnr.ecom.order.domian.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderUpdater {

  private final OrderRepository orderRepository;


  public List<OrderedProduct>  orderUpdateFromStripe(RazorpayPaymentInformation stripeSessionInformation){
    Order order = orderRepository.findByStripeSessionId(stripeSessionInformation).orElseThrow();
    order.validatePayment();
    orderRepository.updateStatus(order.getOrderStatus(),order.getPublicId());

    return order.getOrderedProductList();
  }

  public List<OrderProductQuantity> computeQuantity(List<OrderedProduct> orderedProductList){
    List<OrderProductQuantity> orderProductQuantities=new ArrayList<>();
  for ( OrderedProduct orderedProduct:orderedProductList ){

    OrderProductQuantity productQuantity = OrderProductQuantityBuilder.orderProductQuantity()
      .productPublicId(orderedProduct.getProductPublicId())
      .orderQuentity(orderedProduct.getOrderQuentity()).build();

    orderProductQuantities.add(productQuantity);

  }

  return  orderProductQuantities;
  }
}
