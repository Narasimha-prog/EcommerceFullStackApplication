package com.lnr.ecom.product.domain.infrastrature.seconadary.entity.mapper;

import com.lnr.ecom.product.domain.aggregate.Category;
import com.lnr.ecom.product.domain.aggregate.CategoryBuilder;
import com.lnr.ecom.product.domain.infrastrature.seconadary.entity.CategoryEntity;
import com.lnr.ecom.product.domain.infrastrature.seconadary.entity.CategoryEntityBuilder;
import com.lnr.ecom.product.domain.vo.CategoryName;
import com.lnr.ecom.product.domain.vo.PublicId;

public final class CategoryEntityMapper {

  private CategoryEntityMapper(){

  }


  public static CategoryEntity toEntity(Category category){
    CategoryEntityBuilder categoryEntityBuilder=CategoryEntityBuilder.categoryEntity();

    if(category.getDbId()!=null){
      categoryEntityBuilder.id(category.getDbId());
    }
    return  categoryEntityBuilder.name(category.getName().value())
      .publicId(category.getPublicId().value())
      .build();
  }

  public static  Category toDomain(CategoryEntity categoryEntity){
    return  CategoryBuilder.category()
      .name(new CategoryName(categoryEntity.getName()))
      .publicId(new PublicId(categoryEntity.getPublicId()))
      .dbId(categoryEntity.getId())
      .build();


  }
}
