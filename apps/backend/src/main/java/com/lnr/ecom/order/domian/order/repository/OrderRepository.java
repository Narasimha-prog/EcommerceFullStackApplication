package com.lnr.ecom.order.domian.order.repository;

import com.lnr.ecom.order.domian.order.aggrigate.Order;
import com.lnr.ecom.order.domian.order.aggrigate.RazorpayPaymentInformation;

import com.lnr.ecom.order.domian.order.vo.OrderStatus;
import com.lnr.ecom.order.domian.user.vo.UserPublicId;
import com.lnr.ecom.product.domain.vo.PublicId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface OrderRepository {

  void save(Order order);


  void updateStatus(OrderStatus orderStatus, PublicId orderPublicId);

  Optional<Order> findByRazorPayId(RazorpayPaymentInformation razorSessionInformation);


  Page<Order> findAllByPublicId(UserPublicId userPublicId, Pageable pageable);

  Page<Order> findAll(Pageable pageable);

}
