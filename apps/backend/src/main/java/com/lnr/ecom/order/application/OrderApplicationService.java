package com.lnr.ecom.order.application;

import com.lnr.ecom.order.domian.order.aggrigate.DetailCartItemRequest;
import com.lnr.ecom.order.domian.order.aggrigate.DetailCartRequest;
import com.lnr.ecom.order.domian.order.aggrigate.DetailCartResponse;
import com.lnr.ecom.order.domian.order.mapper.OrderMapper;
import com.lnr.ecom.order.domian.order.service.CartReader;
import com.lnr.ecom.product.application.ProductsApplicationService;
import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.vo.PublicId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderApplicationService {

  private final ProductsApplicationService applicationService;

  private final CartReader cartReader;


  public OrderApplicationService(ProductsApplicationService applicationService) {
    this.cartReader=new CartReader();
    this.applicationService = applicationService;
  }

  public DetailCartResponse getCartDetails(DetailCartRequest cartRequest){
    List<PublicId> publicIds = cartRequest.items().stream().map(DetailCartItemRequest::publicId).toList();

    List<Product> allProductsByIds = applicationService.getAllProductsByIds(publicIds);

    return cartReader.getDetails(allProductsByIds);

  }


}

