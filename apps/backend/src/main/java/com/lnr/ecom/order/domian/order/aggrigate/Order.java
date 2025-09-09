package com.lnr.ecom.order.domian.order.aggrigate;

import com.lnr.ecom.order.domian.order.vo.OrderStatus;
import com.lnr.ecom.order.domian.order.vo.RazorpayPaymentId;

import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.product.domain.vo.PublicId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jilt.Builder;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Order {

  private OrderStatus orderStatus;
  private User user;
  private String razorpayId;
  private PublicId publicId;
  private List<OrderedProduct> orderedProductList;


  public static Order create(User connectedUser, List<OrderedProduct> orderedProductList, RazorpayPaymentId razorpayId){
    return OrderBuilder.order()
      .publicId(new PublicId(UUID.randomUUID()))
      .orderedProductList(orderedProductList)
      .user(connectedUser)
      .orderStatus(OrderStatus.PENDING)
      .razorpayId(razorpayId.value())
      .build();
  }

  public void validatePayment(OrderStatus status){
    this.orderStatus=status;
  }

}
