package com.lnr.ecom.order.domian.order.repository;

import com.lnr.ecom.order.domian.order.aggrigate.Order;

public interface OrderRepository {

  void save(Order order);
}
