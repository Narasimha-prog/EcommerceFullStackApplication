package com.lnr.ecom.order.domian.order.service;

import com.lnr.ecom.order.domian.order.aggrigate.*;
import com.lnr.ecom.order.domian.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderUpdater {

  private final OrderRepository orderRepository;


  public List<OrderedProduct>  orderUpdateFromRazorPay(RazorpayPaymentInformation razorpayPaymentInformation){

    //find order using razorpay id
    Order order = orderRepository.findByRazorPayId(razorpayPaymentInformation).orElseThrow(()->new EntityNotFoundException("Order is not Found with RazorPay id: "+razorpayPaymentInformation.razorpayId()));

    //update payment status using razorPay status in domain
    order.validatePayment(razorpayPaymentInformation.status());

    //update payment status in repository
    orderRepository.updateStatus(order.getOrderStatus(),order.getPublicId());

    return order.getOrderedProductList();
  }

  public List<OrderProductQuantity> computeQuantity(List<OrderedProduct> orderedProductList){

    //take one empty OrderProductQuantity to store (quantity + Product publicId)
    List<OrderProductQuantity> orderProductQuantities=new ArrayList<>();

  for ( OrderedProduct orderedProduct:orderedProductList ){

    OrderProductQuantity productQuantity = OrderProductQuantityBuilder.orderProductQuantity()
                                              //product publicId
                                          .productPublicId(orderedProduct.getProductPublicId())
                                              //quantity
                                          .orderQuentity(orderedProduct.getOrderQuentity()).build();
      //Adding item to it
    orderProductQuantities.add(productQuantity);

  }

  return  orderProductQuantities;
  }
}
