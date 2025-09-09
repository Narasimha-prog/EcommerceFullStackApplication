package com.lnr.ecom.order.infrastrature.primary.order;

import org.jilt.Builder;

@Builder
public record RestOrderItemRead(long quantity,double price,String name) {
}
