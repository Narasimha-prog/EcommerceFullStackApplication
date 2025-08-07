package com.lnr.ecom.product.domain.aggregate;

import com.lnr.ecom.product.domain.vo.ProductSize;
import com.lnr.ecom.product.domain.vo.PublicId;
import org.jilt.Builder;

import java.util.List;

@Builder
public record FilterQuery(PublicId categoryId, List<ProductSize> sizes) {
}
