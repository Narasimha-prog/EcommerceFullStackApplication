package com.lnr.ecom.order.domian.order.aggrigate;

import com.lnr.ecom.product.domain.aggregate.Picture;
import com.lnr.ecom.product.domain.vo.ProductBrand;
import com.lnr.ecom.product.domain.vo.ProductName;
import com.lnr.ecom.product.domain.vo.ProductPrice;
import com.lnr.ecom.product.domain.vo.PublicId;
import com.lnr.ecom.shared.error.domain.Assert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jilt.Builder;

@Builder
@Data
public class ProductCart {


  private ProductName name;

  private ProductBrand brand;

  private Picture  picture;

  private ProductPrice price;
  private PublicId publicId;

  public ProductCart(ProductName name, ProductBrand brand, Picture picture,ProductPrice price, PublicId publicId) {

    assertFields(name,brand,picture,publicId,price);

  }


  public void assertFields(ProductName name, ProductBrand brand, Picture picture, PublicId publicId,ProductPrice price) {
    Assert.notNull("Name",name);
    Assert.notNull("Brand",brand);
    Assert.notNull("picture",picture);
    Assert.notNull("publicId",publicId);
    Assert.notNull("price",price);

  }
}
