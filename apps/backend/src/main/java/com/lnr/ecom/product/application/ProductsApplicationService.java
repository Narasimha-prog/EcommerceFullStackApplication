package com.lnr.ecom.product.application;

import com.lnr.ecom.order.domian.order.aggrigate.OrderProductQuantity;
import com.lnr.ecom.product.domain.aggregate.Category;
import com.lnr.ecom.product.domain.aggregate.FilterQuery;
import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.repository.CategoryRepository;
import com.lnr.ecom.product.domain.repository.ProductRepository;
import com.lnr.ecom.product.domain.service.CategoryCURD;
import com.lnr.ecom.product.domain.service.ProductCURD;
import com.lnr.ecom.product.domain.service.ProductShop;
import com.lnr.ecom.product.domain.service.ProductUpdater;
import com.lnr.ecom.product.domain.vo.PublicId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductsApplicationService {

  private final ProductCURD productCURD;

  private final CategoryCURD categoryCURD;

  private final ProductShop productShop;

  private final ProductUpdater productUpdater;


  public ProductsApplicationService(ProductRepository productRepository, CategoryRepository categoryRepository ) {
    this.productCURD=new ProductCURD(productRepository);
    this.categoryCURD=new CategoryCURD(categoryRepository);
    this.productShop = new ProductShop(productRepository);
    this.productUpdater=new ProductUpdater(productRepository);
  }
@Transactional
  public Product createProduct(Product newProduct){
     return productCURD.save(newProduct);
  }


@Transactional(readOnly = true)
  public Page<Product> findAllProducts(Pageable pageable){

    return productCURD.findAll(pageable);
  }

@Transactional
  public PublicId deleteProduct(PublicId publicId){
    return productCURD.delete(publicId);
  }

  @Transactional
  public Category createCategory(Category category){
    return categoryCURD.save(category);
  }

  @Transactional
  public PublicId deleteCategory(PublicId publicId){
    return categoryCURD.delete(publicId);
  }

  @Transactional(readOnly = true)
  public Page<Category> findAll(Pageable pageable){
    return categoryCURD.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public Page<Category> findAllCategory(Pageable pageable){
    return  categoryCURD.findAll(pageable);

  }

@Transactional(readOnly = true)
  public Page<Product> findFeatuedProducts(Pageable pageable){

   return productShop.getFeaturedProducts(pageable);
  }

  @Transactional(readOnly = true)
  public Product findOne(PublicId publicId){
     return productCURD.findOne(publicId);
  }

  @Transactional(readOnly = true)
public Page<Product> findRelated(Pageable pageable,PublicId poductPublicId){
    return productShop.findRelated(pageable,poductPublicId);
}
@Transactional(readOnly = true)
public Page<Product> filter(Pageable pageable, FilterQuery query){
    return  productShop.filter(pageable,query);

}


@Transactional(readOnly = true)
public List<Product> getAllProductsByIds(List<PublicId> publicIds){
return productCURD.findAllProductsByIds(publicIds);
   }

@Transactional
   public void updateProductQuantity(List<OrderProductQuantity> orderProductQuantities){
    productUpdater.updateProductQuantity(orderProductQuantities);

   }

}
