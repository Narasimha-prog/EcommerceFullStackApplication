package com.lnr.ecom.order.domian.order.service;

import com.lnr.ecom.order.domian.order.aggrigate.Order;
import com.lnr.ecom.order.domian.order.repository.OrderRepository;
import com.lnr.ecom.order.domian.user.vo.UserPublicId;
import com.lnr.ecom.order.infrastrature.secondary.mapper.OrderEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class OrderReader {

  private final OrderRepository orderRepository;

  public Page<Order> findAllByPublicId(UserPublicId userPublicId, Pageable pageable) {
    return orderRepository.findAllByPublicId(userPublicId,pageable);
  }

  public Page<Order> findAll( Pageable pageable) {
    return orderRepository.findAll(pageable);
  }
}
