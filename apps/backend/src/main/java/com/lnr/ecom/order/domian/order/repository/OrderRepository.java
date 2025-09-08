package com.lnr.ecom.order.domian.order.repository;

import com.lnr.ecom.order.domian.order.aggrigate.Order;
import com.lnr.ecom.order.domian.order.aggrigate.StripeSessionInformation;
import com.lnr.ecom.order.domian.order.vo.OrderStatus;
import com.lnr.ecom.product.domain.vo.PublicId;

import java.util.Optional;

public interface OrderRepository {

  void save(Order order);


  void updateStatus(OrderStatus orderStatus, PublicId orderPublicId);

  Optional<Order> findByStripeSessionId(StripeSessionInformation stripeSessionInformation);
}
