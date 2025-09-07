package com.lnr.ecom.order.domian.order.service;


import com.lnr.ecom.order.domian.order.aggrigate.DetailCartItemRequest;
import com.lnr.ecom.order.domian.order.aggrigate.Order;
import com.lnr.ecom.order.domian.order.aggrigate.OrderedProduct;
import com.lnr.ecom.order.domian.order.repository.OrderRepository;
import com.lnr.ecom.order.domian.order.vo.StripeSessionId;
import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.product.domain.aggregate.Product;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderCreator {


  private final OrderRepository orderRepository;



  public StripeSessionId create(List<Product> productsInformation,
                                List<DetailCartItemRequest> items,
                                User connectedUser
                                ){
    List<OrderedProduct> orderedProductList=new ArrayList<>();

    StripeSessionId stripeSessionId=null;

    for(DetailCartItemRequest cartItemRequest:items) {

    com.lnr.ecom.product.domain.aggregate.Product productDetails = productsInformation.stream().filter(product -> product.getPublicId().value().equals(cartItemRequest.publicId().value())).findFirst().orElseThrow();

    OrderedProduct orderedProduct = OrderedProduct.create(cartItemRequest.quentity(), productDetails);

    orderedProductList.add(orderedProduct);
    }


    Order order = Order.create(connectedUser, orderedProductList, null);
    orderRepository.save(order);
return stripeSessionId;
  }


}
