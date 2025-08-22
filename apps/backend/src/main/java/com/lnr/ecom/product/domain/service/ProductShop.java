package com.lnr.ecom.product.domain.service;

import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.repository.ProductRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
public class ProductShop {

  private final ProductRepository productRepository;

  public Page<Product> getFeaturedProducts(Pageable pageable){
   return productRepository.findAllFeaturedProduct(pageable);
  }



}
