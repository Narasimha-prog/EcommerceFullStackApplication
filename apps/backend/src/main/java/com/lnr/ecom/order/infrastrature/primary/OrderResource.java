package com.lnr.ecom.order.infrastrature.primary;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lnr.ecom.order.application.OrderApplicationService;
import com.lnr.ecom.order.domian.order.aggrigate.*;
import com.lnr.ecom.order.domian.order.mapper.DetailCartResponseMapper;

import com.lnr.ecom.order.domian.order.vo.OrderStatus;
import com.lnr.ecom.order.domian.order.vo.RazorpayPaymentId;
import com.lnr.ecom.order.domian.user.vo.*;
import com.lnr.ecom.order.infrastrature.primary.mapper.DetailCartItemRequestMapper;
import com.lnr.ecom.order.infrastrature.primary.mapper.RestOrderReadAdminMapper;
import com.lnr.ecom.order.infrastrature.primary.mapper.RestOrderReadMapper;
import com.lnr.ecom.order.infrastrature.primary.mapper.RestRazorPayMapper;
import com.lnr.ecom.order.infrastrature.primary.order.*;
import com.lnr.ecom.product.domain.vo.PublicId;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

import static com.lnr.ecom.product.infrastrature.primary.ProductAdminResource.ROLE_ADMIN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@Slf4j
public class OrderResource {

  private final OrderApplicationService applicationService;

  @Value("${Razorpay-Value}")
  private String secretKey;

  @GetMapping("/get-cart-details")
  public ResponseEntity<RestDetailCartResponse> getDetails(@RequestParam List<UUID> productIds) {

    List<DetailCartItemRequest> cartItemRequestList = productIds.stream().map(u -> new DetailCartItemRequest(new PublicId(u), 1)).toList();

    DetailCartRequest detailCartRequest = DetailCartRequestBuilder.detailCartRequest()
      .items(cartItemRequestList).build();

    DetailCartResponse cartDetails = applicationService.getCartDetails(detailCartRequest);
    return ResponseEntity.ok(DetailCartResponseMapper.toDomain(cartDetails));
  }



  @PostMapping("/webhook")
  public ResponseEntity<Void> webhookRazorpay(@RequestBody String payload,
                                              @RequestHeader("X-Razorpay-Signature") String razorpaySignature) {
    try {
      boolean isValidSignature = Utils.verifyWebhookSignature(payload, razorpaySignature, secretKey);
      if (!isValidSignature) {
        return ResponseEntity.badRequest().build();
      }

      ObjectMapper mapper = new ObjectMapper();
      JsonNode root = mapper.readTree(payload);

      String eventType = root.get("event").asText();
      log.info("Payment status..{}", eventType);

      switch (eventType) {
        case "order.paid":
        case "payment.captured":
          JsonNode paymentEntity = root.path("payload").path("payment").path("entity");
          log.info("💳 Payment entity: {}", paymentEntity.toPrettyString());
          handleOrderPaid(paymentEntity);
          break;
        default:
          log.warn("Unhandled event type: {}", eventType);
      }

      return ResponseEntity.ok().build();

    } catch (Exception e) {
      log.error("Error while processing Razorpay webhook: ", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  private void handleOrderPaid(JsonNode paymentEntity) {
    String razorpayPaymentId = paymentEntity.get("order_id").asText();
    String razorpayStatus = paymentEntity.get("status").asText();

    OrderStatus orderStatus = mapRazorpayStatus(razorpayStatus);

    // Build payment information safely
    RazorpayPaymentInformation paymentInformation = RazorpayPaymentInformationBuilder
      .razorpayPaymentInformation()
      .razorpayId(new RazorpayPaymentId(razorpayPaymentId))
      .status(orderStatus) // <-- map status here
      .build();

    this.applicationService.updateOrder(paymentInformation);
  }

  /** Map Razorpay status to internal OrderStatus */
  private OrderStatus mapRazorpayStatus(String razorpayStatus) {
    if ("captured".equalsIgnoreCase(razorpayStatus)) {
      return OrderStatus.PAID;
    } else {
      return OrderStatus.PENDING;
    }
  }



  @PostMapping("/init-payment")
  public ResponseEntity<RestRazorPayId> initPayment(@RequestBody List<RestCartItemRequest> items) {

    //product item + quantity
    List<DetailCartItemRequest> domainList = DetailCartItemRequestMapper.toDomainList(items);

    try {
      RazorpayPaymentId order = applicationService.createOrder(domainList);

      RestRazorPayId restRazorPayId = RestRazorPayMapper.toDomain(order);
log.info("intialize payment razorPyaid: {}",restRazorPayId);
      return ResponseEntity.ok(restRazorPayId);
    } catch (RazorpayException e) {
      return ResponseEntity.badRequest().build();
    }
  }



@GetMapping("/user")
public ResponseEntity<Page<ResOrderRead>> getOrdersForConnectedUsers(Pageable pageable){

  Page<Order> ordersForConnectedUser = applicationService.findOrdersForConnectedUser(pageable);
  PageImpl<ResOrderRead> resOrderReads = new PageImpl<>(
    ordersForConnectedUser.getContent().stream().map(RestOrderReadMapper::toRest).toList(),
    pageable,
    ordersForConnectedUser.getTotalElements()
  );

  return ResponseEntity.ok(resOrderReads);
}


  @GetMapping("/admin")
  @PreAuthorize("hasAnyRole('"+ROLE_ADMIN+"')")
  public ResponseEntity<Page<RestOrderReadAdmin>> getOrdersForAdmin(Pageable pageable){

    Page<Order> ordersForAdmin = applicationService.findOrdersForAdmin(pageable);
    PageImpl<RestOrderReadAdmin> resOrderReads = new PageImpl<>(
      ordersForAdmin.getContent().stream().map(RestOrderReadAdminMapper::toRest).toList(),
      pageable,
      ordersForAdmin.getTotalElements()
    );

    return ResponseEntity.ok(resOrderReads);
  }


}
