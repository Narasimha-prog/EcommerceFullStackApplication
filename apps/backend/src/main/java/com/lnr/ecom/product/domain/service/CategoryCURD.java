package com.lnr.ecom.product.domain.service;

import com.lnr.ecom.product.domain.aggregate.Category;
import com.lnr.ecom.product.domain.repository.CategoryRepository;
import com.lnr.ecom.product.domain.vo.PublicId;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class CategoryCURD {

  private final com.lnr.ecom.product.domain.repository.CategoryRepository categoryRepository;

  public Category save(Category category){
    category.initDefaultFields();
   return categoryRepository.save(category);
  }

  public Page<Category> findAll(Pageable pageable){
    return categoryRepository.finadAll(pageable);
  }

  public PublicId delete(PublicId categoryId){
    int noOfRowsDeleted =categoryRepository.delete(categoryId);

    if(noOfRowsDeleted!=1){
      throw  new EntityNotFoundException(String.format("No Category Deleted with Id %s",categoryId));
    }
    return categoryId;
  }


}
