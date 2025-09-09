package com.lnr.ecom.order.domian.order.aggrigate;

import com.lnr.ecom.order.domian.order.vo.OrderPrice;
import com.lnr.ecom.order.domian.order.vo.OrderQuentity;
import com.lnr.ecom.order.domian.order.vo.ProductPublicId;
import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.vo.ProductName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jilt.Builder;

@Builder
@RequiredArgsConstructor
@Data
public class OrderedProduct {

  private final ProductPublicId productPublicId;
  private final OrderPrice productPrice;
  private final OrderQuentity orderQuentity;
  private final ProductName productName;

public static OrderedProduct create(long quantity, Product product){
  return OrderedProductBuilder.orderedProduct()
    .orderQuentity(new OrderQuentity(quantity))
    .productPrice(new OrderPrice(product.getProductPrice().value()))
    .productPublicId(new ProductPublicId(product.getPublicId().value()))
    .productName(product.getProductName())
    .build();
}


}
