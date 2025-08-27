package com.lnr.ecom.product.domain.service;

import com.lnr.ecom.product.domain.aggregate.FilterQuery;
import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.repository.ProductRepository;
import com.lnr.ecom.product.domain.vo.PublicId;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


@RequiredArgsConstructor
public class ProductShop {

  private final ProductRepository productRepository;

  public Page<Product> getFeaturedProducts(Pageable pageable){
   return productRepository.findAllFeaturedProduct(pageable);
  }

public Page<Product> findRelated(Pageable pageable, PublicId publicId){
  Optional<Product> product = productRepository.findOne(publicId);
  if(product.isPresent()){
    return productRepository.findByCategoryExcludingOne(pageable,product.get().getCategory().getPublicId(),publicId);
  }else {
    throw new EntityNotFoundException(String.format("Product not found with this ID %s",publicId));
  }
}

public Page<Product> filter(Pageable pageable, FilterQuery query){
    return productRepository.findByCategoryAndSize(pageable,query);
}

}
