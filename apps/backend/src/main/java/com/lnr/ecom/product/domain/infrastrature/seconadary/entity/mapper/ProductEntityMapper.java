package com.lnr.ecom.product.domain.infrastrature.seconadary.entity.mapper;


import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.aggregate.ProductBuilder;
import com.lnr.ecom.product.domain.infrastrature.seconadary.entity.CategoryEntity;
import com.lnr.ecom.product.domain.infrastrature.seconadary.entity.PictureEntity;
import com.lnr.ecom.product.domain.infrastrature.seconadary.entity.ProductEnity;
import com.lnr.ecom.product.domain.infrastrature.seconadary.entity.ProductEnityBuilder;
import com.lnr.ecom.product.domain.vo.*;

public final class  ProductEntityMapper {

  private ProductEntityMapper(){

  }


  public static ProductEnity toEntity(Product product) {
    ProductEnityBuilder productEntityBuilder = ProductEnityBuilder.productEnity();

    if(product.getDbId() != null) {
      productEntityBuilder.id(product.getDbId());
    }

    return productEntityBuilder
      .brand(product.getProductBrand().value())
      .color(product.getProductColor().value())
      .description(product.getProductDescription().value())
      .name(product.getProductName().value())
      .price(product.getProductPrice().value())
      .size(product.getProductSize())
      .publicId(product.getPublicId().value())
      .category(CategoryEntityMapper.toEntity (product.getCategory()))
      .pictureEntities(PictureEntityMapper.toEntityList(product.getPictures()))
      .featured(product.isFeatured())
      .nbInStock(product.getNbInStack())
      .build();
  }

  public static Product to(ProductEnity productEntity) {
    return ProductBuilder.product()
      .productBrand(new ProductBrand(productEntity.getBrand()))
      .productColor(new ProductColor(productEntity.getColor()))
      .productDescription(new ProductDescription(productEntity.getDescription()))
      .productName(new ProductName(productEntity.getName()))
      .productPrice(new ProductPrice(productEntity.getPrice()))
      .productSize(productEntity.getSize())
      .publicId(new PublicId(productEntity.getPublicId()))
      .category(CategoryEntityMapper.toDomain(productEntity.getCategory()))
      .pictures(PictureEntityMapper.toDomainList(productEntity.getPictureEntities()))
      .featured(productEntity.getFeatured())
      .nbInStack(productEntity.getNbInStock())
      .build();
  }


}
