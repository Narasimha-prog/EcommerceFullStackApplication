package com.lnr.ecom.product.infrastrature.seconadary.entity.repository;

import com.lnr.ecom.product.infrastrature.seconadary.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity,Long> {


  Optional<CategoryEntity> findByPublicId(UUID publicId);

  int deleteByPublicId(UUID publicId);



}
