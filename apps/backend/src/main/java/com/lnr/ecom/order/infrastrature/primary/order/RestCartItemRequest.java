package com.lnr.ecom.order.infrastrature.primary.order;

import org.jilt.Builder;

import java.util.UUID;
@Builder
public record RestCartItemRequest(UUID publicId,long quantity) {


}
