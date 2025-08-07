package com.lnr.ecom.product.infrastrature.seconadary.entity.repository;

import com.lnr.ecom.product.domain.aggregate.Category;
import com.lnr.ecom.product.domain.repository.CategoryRepository;
import com.lnr.ecom.product.domain.vo.PublicId;
import com.lnr.ecom.product.infrastrature.seconadary.entity.CategoryEntity;
import com.lnr.ecom.product.infrastrature.seconadary.entity.mapper.CategoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpringDataCategoryRepository implements CategoryRepository {

  private final JpaCategoryRepository categoryRepository;


  @Override
  public Page<Category> finadAll(Pageable pageable) {
    return categoryRepository.findAll(pageable).map(CategoryEntityMapper::toDomain);
  }

  @Override
  public int delete(PublicId publicId) {
    return categoryRepository.deleteByPublicId(publicId.value());
  }


  @Override
  public Category save(Category categoryToCreate) {
    CategoryEntity categoryToSave= CategoryEntityMapper.toEntity(categoryToCreate);
    CategoryEntity savedCategory=categoryRepository.save(categoryToSave);
    return CategoryEntityMapper.toDomain(savedCategory);
  }
}
