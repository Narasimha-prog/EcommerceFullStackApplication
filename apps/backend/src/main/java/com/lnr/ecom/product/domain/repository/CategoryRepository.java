package com.lnr.ecom.product.domain.repository;

import com.lnr.ecom.product.domain.aggregate.Category;
import com.lnr.ecom.product.domain.vo.PublicId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryRepository {
  Page<Category> finadAll(Pageable pageable);

int delete(PublicId publicId);

Category save(Category categoryToCreate);
}
