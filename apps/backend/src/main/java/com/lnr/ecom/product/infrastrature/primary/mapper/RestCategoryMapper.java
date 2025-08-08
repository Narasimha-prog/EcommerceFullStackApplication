package com.lnr.ecom.product.infrastrature.primary.mapper;

import com.lnr.ecom.product.domain.aggregate.Category;
import com.lnr.ecom.product.domain.aggregate.CategoryBuilder;
import com.lnr.ecom.product.domain.vo.CategoryName;
import com.lnr.ecom.product.domain.vo.PublicId;
import com.lnr.ecom.product.infrastrature.primary.RestCategory;
import com.lnr.ecom.product.infrastrature.primary.RestCategoryBuilder;

public final class RestCategoryMapper {

private RestCategoryMapper(){

}
  public static Category toDomin(RestCategory restCategory){
    CategoryBuilder categoryBuilder=CategoryBuilder.category();

    if(restCategory.name()!=null){
      categoryBuilder.name(new CategoryName(restCategory.name()));
    }

    if(restCategory.publicId()!=null){
      categoryBuilder.publicId(new PublicId(restCategory.publicId()));
    }

    return categoryBuilder.build();
  }

  public static RestCategory toRestDomain(Category category){
    RestCategoryBuilder categoryBuilder=RestCategoryBuilder.restCategory();

    if(category.getName()!=null){
      categoryBuilder.name(category.getName().value());
    }

    return categoryBuilder.publicId(category.getPublicId().value()).build();
  }

}
