package com.lnr.ecom.product.repository;

import com.lnr.ecom.product.aggregate.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryRepository {
  Page<Category> finadAll(Pageable pageable);


}
