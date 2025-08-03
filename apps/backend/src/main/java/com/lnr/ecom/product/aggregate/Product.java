package com.lnr.ecom.product.aggregate;

import com.lnr.ecom.product.vo.*;
import com.lnr.ecom.shared.error.domain.Assert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jilt.Builder;

import java.util.List;

@Builder
@Setter
@Getter
public class Product {
  private final ProductBrand productBrand;

  private final ProductColor productColor;

  private final ProductDescription productDescription;

  private final ProductName productName;

  private final ProductPrice productPrice;

  private final ProductSize productSize;

  private final Category category;

  private final List<Picture> pictures;

  private  Long dbId;

  private boolean featured;

  private PublicId publicId;

  private int nbInStack;


  public Product(ProductBrand productBrand, ProductColor productColor, ProductDescription productDescription, ProductName productName, ProductPrice productPrice, ProductSize productSize, Category category, List<Picture> pictures, Long dbId, boolean featured, PublicId publicId, int nbInStack) {
    assertMandatoryFields(productBrand,productColor,productDescription,productName,productPrice,productSize,category,pictures,featured,nbInStack);
    this.productBrand = productBrand;
    this.productColor = productColor;
    this.productDescription = productDescription;
    this.productName = productName;
    this.productPrice = productPrice;
    this.productSize = productSize;
    this.category = category;
    this.pictures = pictures;
    this.dbId = dbId;
    this.featured = featured;
    this.publicId = publicId;
    this.nbInStack = nbInStack;
  }

  private void assertMandatoryFields(

    ProductBrand productBrand,
    ProductColor productColor,
    ProductDescription productDescription,
    ProductName productName,
    ProductPrice productPrice,
    ProductSize productSize,
    Category category,
    List<Picture> pictures,
    boolean featured,
    int nbInStack
  ){

    Assert.notNull("productBrand",productBrand);
    Assert.notNull("productColor",productColor);
    Assert.notNull("productDescription",productDescription);
    Assert.notNull("productName",productName);
    Assert.notNull("productPrice",productPrice);
    Assert.notNull("productSize",productSize);
    Assert.notNull("category",category);
    Assert.notNull("pictures",pictures);
    Assert.notNull("featured",featured);
    Assert.notNull("nbInStack",nbInStack);
  }
}
