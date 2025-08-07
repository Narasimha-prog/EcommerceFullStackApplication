package com.lnr.ecom.product.domain.application;

import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.service.CategoryCURD;
import com.lnr.ecom.product.domain.service.ProductCURD;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductsApplicationService {

  private final ProductCURD productCURD;

  private final CategoryCURD categoryCURD;

  public Product createProduct(Product newProduct){
     return productCURD.save(newProduct);
  }


}
