package com.lnr.ecom.order.domian.order.aggrigate;

import org.jilt.Builder;

import java.util.List;

@Builder
public record DetailCartRequest(List<DetailCartItemRequest> items) {
}
