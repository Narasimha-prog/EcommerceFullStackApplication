package com.lnr.ecom.order.application;

import com.lnr.ecom.order.domian.order.aggrigate.*;
import com.lnr.ecom.order.domian.order.repository.OrderRepository;
import com.lnr.ecom.order.domian.order.service.CartReader;
import com.lnr.ecom.order.domian.order.service.OrderCreator;
import com.lnr.ecom.order.domian.order.service.OrderReader;
import com.lnr.ecom.order.domian.order.service.OrderUpdater;
import com.lnr.ecom.order.domian.order.vo.RazorpayPaymentId;
import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.order.infrastrature.primary.order.ResOrderRead;
import com.lnr.ecom.order.infrastrature.primary.order.RestOrderItemRead;
import com.lnr.ecom.order.infrastrature.secondary.service.razorpay.RazorPayService;
import com.lnr.ecom.product.application.ProductsApplicationService;
import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.vo.PublicId;
import com.razorpay.RazorpayException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderApplicationService {

  private final ProductsApplicationService applicationService;
  private final UserApplicationService userApplicationService;
  private final CartReader cartReader;
  private final OrderCreator orderCreator;
  private final OrderUpdater  orderUpdater;
  private final OrderReader orderReader;


  public OrderApplicationService(ProductsApplicationService applicationService, UserApplicationService userApplicationService, OrderRepository orderRepository, RazorPayService razorPayService) {
    this.userApplicationService=userApplicationService;
    this.cartReader=new CartReader();
    this.applicationService = applicationService;
    this.orderCreator=new OrderCreator(orderRepository,razorPayService);
    this.orderUpdater=new OrderUpdater(orderRepository);
    this.orderReader=new OrderReader(orderRepository);


  }
@Transactional(readOnly = true)
  public DetailCartResponse getCartDetails(DetailCartRequest cartRequest){
    List<PublicId> publicIds = cartRequest.items().stream().map(DetailCartItemRequest::publicId).toList();

    List<Product> allProductsByIds = applicationService.getAllProductsByIds(publicIds);

    return cartReader.getDetails(allProductsByIds);
  }


  @Transactional
  public RazorpayPaymentId createOrder(List<DetailCartItemRequest> detailCartItemRequests) throws RazorpayException {

    User authenticatedUser=userApplicationService.getAuthenticatedUser();

    List<PublicId> publicIds=detailCartItemRequests.stream().map(DetailCartItemRequest::publicId).toList();

    List<Product> products=applicationService.getAllProductsByIds(publicIds);

return  orderCreator.create(products,detailCartItemRequests,authenticatedUser);
  }
@Transactional
public void updateOrder(RazorpayPaymentInformation stripeSessionInformation) {
  List<OrderedProduct> orderedProductList = orderUpdater.orderUpdateFromStripe(stripeSessionInformation);

  List<OrderProductQuantity> orderProductQuantities = this.orderUpdater.computeQuantity(orderedProductList);
  this.applicationService.updateProductQuantity(orderProductQuantities);
  this.userApplicationService.updateUserAddress(stripeSessionInformation.userAddressToUpdate());
}

@Transactional(readOnly = true)
public Page<Order> findOrdersForConnectedUser(Pageable pageable){
    User authenticatedUser=userApplicationService.getAuthenticatedUser();
  return orderReader.findAllByPublicId(authenticatedUser.getPublicId(), pageable);

  }

  @Transactional(readOnly = true)
  public Page<Order> findOrdersForAdmin(Pageable pageable){

    return orderReader.findAll(pageable);

  }

}

