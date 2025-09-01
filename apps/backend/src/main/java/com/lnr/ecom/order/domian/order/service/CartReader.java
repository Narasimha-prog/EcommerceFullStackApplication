package com.lnr.ecom.order.domian.order.service;

import com.lnr.ecom.order.domian.order.aggrigate.DetailCartResponse;
import com.lnr.ecom.order.domian.order.aggrigate.DetailCartResponseBuilder;
import com.lnr.ecom.order.domian.order.aggrigate.ProductCart;
import com.lnr.ecom.order.domian.order.mapper.OrderMapper;
import com.lnr.ecom.product.domain.aggregate.Product;

import java.util.List;

public class CartReader {

  public DetailCartResponse getDetails(List<Product> products){
    List<ProductCart> list = products.stream().map(OrderMapper::toOrder).toList();

    return DetailCartResponseBuilder.detailCartResponse()
      .products(list).build();
  }
}
