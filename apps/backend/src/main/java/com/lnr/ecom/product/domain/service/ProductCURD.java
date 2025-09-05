package com.lnr.ecom.product.domain.service;

import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.repository.ProductRepository;
import com.lnr.ecom.product.domain.vo.PublicId;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductCURD {

  private final ProductRepository productRepository;

  public Product save(Product newProduct){
    newProduct.initDefaultFields();
    return productRepository.save(newProduct);
  }

  public Page<Product> findAll(Pageable pageable){
    return productRepository.findAll(pageable);
  }

  public PublicId delete(PublicId productId){
    int noOfRowsDeleted=productRepository.delete(productId);
    if(noOfRowsDeleted!=1){
      throw  new EntityNotFoundException(String.format("Product Not Found with Id %s",productId));
    }

    return productId;
  }


  public Product findOne(PublicId publicId){
    return  productRepository.findOne(publicId).orElseThrow(()->new EntityNotFoundException(String.format("Product not found with this ID %s",publicId)));
  }


  public List<Product> findAllProductsByIds(List<PublicId> publicIds){
    return productRepository.findByPublicIds(publicIds);
  }
}
