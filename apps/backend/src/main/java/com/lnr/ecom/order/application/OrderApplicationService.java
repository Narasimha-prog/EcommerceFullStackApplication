package com.lnr.ecom.order.application;

import com.lnr.ecom.order.domian.order.aggrigate.DetailCartRequest;
import com.lnr.ecom.order.domian.order.aggrigate.DetailCartResponse;
import com.lnr.ecom.order.domian.order.service.CartReader;
import com.lnr.ecom.product.application.ProductsApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderApplicationService {

  private final ProductsApplicationService applicationService;

  private final CartReader cartReader;


  public OrderApplicationService(ProductsApplicationService applicationService) {
    this.cartReader=new CartReader();
    this.applicationService = applicationService;
  }

  public DetailCartResponse getCartDetails(DetailCartRequest cartRequest){
    return null;
  }
}
