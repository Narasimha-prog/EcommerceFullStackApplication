package com.lnr.ecom.order.infrastrature.primary.order;

import com.lnr.ecom.order.domian.order.vo.OrderStatus;
import org.jilt.Builder;

import java.util.List;
import java.util.UUID;
@Builder
public record RestOrderReadAdmin(UUID publicId,
                                 OrderStatus status,
                                 List<RestOrderItemRead> orderedItems,
                                 String address,
                                 String email
                                 ) {
}
