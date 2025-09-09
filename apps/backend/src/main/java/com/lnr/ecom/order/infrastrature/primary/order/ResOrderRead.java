package com.lnr.ecom.order.infrastrature.primary.order;

import com.lnr.ecom.order.domian.order.vo.OrderStatus;
import com.lnr.ecom.shared.error.domain.Assert;
import org.jilt.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ResOrderRead(UUID publicId, OrderStatus status, List<RestOrderItemRead> orderedItems) {

  public ResOrderRead {
    Assert.notNull("OrderPublicId",publicId);
    Assert.notNull("status",status);
    Assert.notNull("orderItems",orderedItems);
  }
}
