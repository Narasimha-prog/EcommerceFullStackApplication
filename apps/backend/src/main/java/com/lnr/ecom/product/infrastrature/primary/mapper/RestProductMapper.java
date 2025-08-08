package com.lnr.ecom.product.infrastrature.primary.mapper;

import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.aggregate.ProductBuilder;
import com.lnr.ecom.product.domain.vo.*;
import com.lnr.ecom.product.infrastrature.primary.RestProduct;
import com.lnr.ecom.product.infrastrature.primary.RestProductBuilder;

public final class RestProductMapper {

  private RestProductMapper(){}

  public static Product toDomain(RestProduct restProduct){

   ProductBuilder productBuilder=  ProductBuilder.product()
      .productBrand(new ProductBrand(restProduct.getBrand()))
      .productName(new ProductName(restProduct.getName()))
      .productPrice(new ProductPrice(restProduct.getPrice()))
      .productSize(restProduct.getSize())
      .productDescription(new ProductDescription(restProduct.getDescription()))
      .productColor(new ProductColor(restProduct.getColor()))
      .nbInStack(restProduct.getNbInStock())
      .category(RestCategoryMapper.toDomin( restProduct.getCategory()))
      .featured(restProduct.isFeatured());

    if(restProduct.getPublicId()!=null){
       productBuilder.publicId(new PublicId(restProduct.getPublicId()));
    }

    if(restProduct.getPitchureList()!= null){
     productBuilder.pictures(RestPictureMapper.toDomianList(restProduct.getPitchureList()));
    }
    return productBuilder.build();
  }

  public static RestProduct toRestDomain(Product product){
return  RestProductBuilder.restProduct()

      .brand(product.getProductBrand().value())
      .name(product.getProductName().value())
      .color(product.getProductColor().value())
      .description(product.getProductDescription().value())
      .price(product.getProductPrice().value())
      .featured(product.isFeatured())
      .category(RestCategoryMapper.toRestDomain(product.getCategory()))
      .size(product.getProductSize())
      .pitchureList(RestPictureMapper.toRestDomainList(product.getPictures()))
      .publicId(product.getPublicId().value())
      .nbInStock(product.getNbInStack())
      .build();
  }
}
