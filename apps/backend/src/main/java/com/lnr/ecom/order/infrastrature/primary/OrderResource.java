package com.lnr.ecom.order.infrastrature.primary;

import com.lnr.ecom.order.application.OrderApplicationService;
import com.lnr.ecom.order.domian.order.aggrigate.DetailCartItemRequest;
import com.lnr.ecom.order.domian.order.aggrigate.DetailCartRequest;
import com.lnr.ecom.order.domian.order.aggrigate.DetailCartRequestBuilder;
import com.lnr.ecom.order.domian.order.aggrigate.DetailCartResponse;
import com.lnr.ecom.order.domian.order.mapper.DetailCartResponseMapper;
import com.lnr.ecom.order.domian.order.vo.StripeSessionId;
import com.lnr.ecom.order.infrastrature.primary.mapper.DetailCartItemRequestMapper;
import com.lnr.ecom.order.infrastrature.primary.mapper.RestStripeSessionMapper;
import com.lnr.ecom.order.infrastrature.primary.order.RestCartItemRequest;
import com.lnr.ecom.order.infrastrature.primary.order.RestDetailCartResponse;
import com.lnr.ecom.order.infrastrature.primary.order.RestStripeSession;
import com.lnr.ecom.product.domain.vo.PublicId;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderResource {

  private final OrderApplicationService applicationService;

@GetMapping("/get-cart-details")
public ResponseEntity<RestDetailCartResponse> getDetails(@RequestParam List<UUID> productIds){

  List<DetailCartItemRequest> cartItemRequestList = productIds.stream().map(u -> new DetailCartItemRequest(new PublicId(u), 1)).toList();

  DetailCartRequest detailCartRequest= DetailCartRequestBuilder.detailCartRequest()
    .items(cartItemRequestList).build();

  DetailCartResponse cartDetails = applicationService.getCartDetails(detailCartRequest);
  return  ResponseEntity.ok(DetailCartResponseMapper.toDomain(cartDetails));
}

@PostMapping("/init-payment")
public ResponseEntity<RestStripeSession>  initPayment(@RequestBody List<RestCartItemRequest> items){

  List<DetailCartItemRequest> domainList = DetailCartItemRequestMapper.toDomainList(items);

  try {
    StripeSessionId order = applicationService.createOrder(domainList);
    RestStripeSession restStripeSession = RestStripeSessionMapper.toDomain(order);
    return ResponseEntity.ok(restStripeSession);
  } catch (RazorpayException e) {
    return ResponseEntity.badRequest().build();
  }
}


}
