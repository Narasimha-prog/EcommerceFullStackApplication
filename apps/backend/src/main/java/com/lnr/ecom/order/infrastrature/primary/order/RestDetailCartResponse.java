package com.lnr.ecom.order.infrastrature.primary.order;

import org.jilt.Builder;

import java.util.List;
@Builder
public record RestDetailCartResponse(List<RestProductCart> products) {
}
