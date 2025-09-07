package com.lnr.ecom.order.infrastrature.secondary.service.razorpay;

import com.lnr.ecom.order.domian.order.aggrigate.DetailCartItemRequest;
import com.lnr.ecom.order.domian.order.vo.StripeSessionId;
import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.product.domain.aggregate.Product;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RazorPayService {

  @Value("${Razorpay-Key}")
  private String apiKey;

  @Value("${Razorpay-Value}")
  private String apiValue;

  private RazorpayClient razorpayClient;

  @PostConstruct
  public void init() throws RazorpayException {
    this.razorpayClient = new RazorpayClient(apiKey, apiValue);
  }


  @Getter
  public  String razorPayStatus;


  /**
   * Create a Razorpay Order and return session info for frontend
   */
  public StripeSessionId createPayment(User connectedUser, List<Product> products, List<DetailCartItemRequest> items) throws RazorpayException {

    // Calculate total amount (paise)
    double totalAmount = items.stream()
      .mapToDouble(item -> (item.quentity() * getProductPrice(products, item)))
      .sum() * 100;

    JSONObject orderRequest = new JSONObject();
    orderRequest.put("amount", totalAmount); // amount in paise
    orderRequest.put("currency", "INR");
    orderRequest.put("receipt", connectedUser.getPublicId().userPublicId().toString());
    orderRequest.put("payment_capture", 1);

    // Create Razorpay Order
    com.razorpay.Order razorpayOrder = razorpayClient.orders.create(orderRequest);
    razorPayStatus = razorpayOrder.get("status");

    return new StripeSessionId(razorpayOrder.get("id"));
  }

  // Helper to get product price
  private double getProductPrice(List<Product> products, DetailCartItemRequest item) {
    return products.stream()
      .filter(p -> p.getPublicId().value().equals(item.publicId().value()))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("Product not found: " + item.publicId().value()))
      .getProductPrice().value(); // Assuming ProductPrice has a value() method returning int
  }
}
