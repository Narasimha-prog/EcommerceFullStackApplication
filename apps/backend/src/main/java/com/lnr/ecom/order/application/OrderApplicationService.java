package com.lnr.ecom.order.application;

import com.lnr.ecom.order.domian.order.aggrigate.DetailCartItemRequest;
import com.lnr.ecom.order.domian.order.aggrigate.DetailCartRequest;
import com.lnr.ecom.order.domian.order.aggrigate.DetailCartResponse;
import com.lnr.ecom.order.domian.order.repository.OrderRepository;
import com.lnr.ecom.order.domian.order.service.CartReader;
import com.lnr.ecom.order.domian.order.service.OrderCreator;
import com.lnr.ecom.order.domian.order.vo.StripeSessionId;
import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.order.infrastrature.secondary.service.razorpay.RazorPayService;
import com.lnr.ecom.product.application.ProductsApplicationService;
import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.vo.PublicId;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderApplicationService {

  private final ProductsApplicationService applicationService;
  private final UserApplicationService userApplicationService;
  private final CartReader cartReader;
  private final OrderCreator orderCreator;


  public OrderApplicationService(ProductsApplicationService applicationService, UserApplicationService userApplicationService, OrderRepository orderRepository, RazorPayService razorPayService) {
    this.userApplicationService=userApplicationService;
    this.cartReader=new CartReader();
    this.applicationService = applicationService;
    this.orderCreator=new OrderCreator(orderRepository,razorPayService);


  }
@Transactional(readOnly = true)
  public DetailCartResponse getCartDetails(DetailCartRequest cartRequest){
    List<PublicId> publicIds = cartRequest.items().stream().map(DetailCartItemRequest::publicId).toList();

    List<Product> allProductsByIds = applicationService.getAllProductsByIds(publicIds);

    return cartReader.getDetails(allProductsByIds);
  }


  @Transactional
  public StripeSessionId createOrder(List<DetailCartItemRequest> detailCartItemRequests) throws RazorpayException {

    User authenticatedUser=userApplicationService.getAuthenticatedUser();

    List<PublicId> publicIds=detailCartItemRequests.stream().map(DetailCartItemRequest::publicId).toList();

    List<Product> products=applicationService.getAllProductsByIds(publicIds);

return  orderCreator.create(products,detailCartItemRequests,authenticatedUser);
  }



}

