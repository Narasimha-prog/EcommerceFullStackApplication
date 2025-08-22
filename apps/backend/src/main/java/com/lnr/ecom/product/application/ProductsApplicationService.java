package com.lnr.ecom.product.application;

import com.lnr.ecom.product.domain.aggregate.Category;
import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.repository.CategoryRepository;
import com.lnr.ecom.product.domain.repository.ProductRepository;
import com.lnr.ecom.product.domain.service.CategoryCURD;
import com.lnr.ecom.product.domain.service.ProductCURD;
import com.lnr.ecom.product.domain.service.ProductShop;
import com.lnr.ecom.product.domain.vo.PublicId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductsApplicationService {

  private final ProductCURD productCURD;

  private final CategoryCURD categoryCURD;

  private final ProductShop productShop;


  public ProductsApplicationService(ProductRepository productRepository, CategoryRepository categoryRepository ) {
    this.productCURD=new ProductCURD(productRepository);
    this.categoryCURD=new CategoryCURD(categoryRepository);
    this.productShop = new ProductShop(productRepository);
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
}
