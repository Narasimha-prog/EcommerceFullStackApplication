package com.lnr.ecom.order.domian.order.aggrigate;

import com.lnr.ecom.order.domian.order.vo.OrderStatus;
import com.lnr.ecom.order.domian.order.vo.StripeSessionId;
import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.product.domain.vo.PublicId;
import lombok.AllArgsConstructor;
import org.jilt.Builder;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
public class Order {
  private OrderStatus orderStatus;

  private User user;

  private String stripeId;

  private PublicId publicId;

  private List<OrderedProduct> orderedProductList;


  public Order create(User connectedUser, List<OrderedProduct> orderedProductList, StripeSessionId stripeSessionId){
    return OrderBuilder.order()
      .publicId(new PublicId(UUID.randomUUID()))
      .orderedProductList(orderedProductList)
      .user(connectedUser)
      .orderStatus(OrderStatus.PENDING)
      .stripeId(stripeSessionId.value())
      .build();
  }

  public void validatePayment(){
    this.orderStatus=OrderStatus.PAID;
  }

}
