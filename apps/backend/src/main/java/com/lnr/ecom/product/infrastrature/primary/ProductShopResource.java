package com.lnr.ecom.product.infrastrature.primary;

import com.lnr.ecom.product.application.ProductsApplicationService;
import com.lnr.ecom.product.domain.aggregate.FilterQuery;
import com.lnr.ecom.product.domain.aggregate.FilterQueryBuilder;
import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.vo.ProductSize;
import com.lnr.ecom.product.domain.vo.PublicId;
import com.lnr.ecom.product.infrastrature.primary.mapper.RestProductMapper;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products-shop")
@RequiredArgsConstructor
public class ProductShopResource {

private final ProductsApplicationService applicationService;


@GetMapping("/featured")
public ResponseEntity<Page<RestProduct>> getFeatuedProducts(
  @ParameterObject
  @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10)Pageable pageable){

   Page<Product> featuredProduct=  applicationService.findFeatuedProducts(pageable);

 Page<RestProduct> productPage=  new PageImpl<RestProduct>(
     featuredProduct.getContent().stream().map(RestProductMapper::toRestDomain).toList(),
     pageable,
     featuredProduct.getTotalElements()

   ){};

 return ResponseEntity.ok(productPage);
}

@GetMapping("/find-one")
public ResponseEntity<RestProduct> findOne(@RequestParam("publicId")UUID id){


  Product product = applicationService.findOne(new PublicId(id));
  return ResponseEntity.ok(RestProductMapper.toRestDomain(product));
}

  @GetMapping("/related")
  public ResponseEntity<Page<RestProduct>> relatedProducts(Pageable pageable,@RequestParam("publicId")UUID id){

    Page<Product> productPage = applicationService.findRelated(pageable,new PublicId(id));

    PageImpl<RestProduct> restProducts = new PageImpl<>(
      productPage.getContent().stream().map(RestProductMapper::toRestDomain).toList(),
      pageable,
      productPage.getNumberOfElements()
    );

    return ResponseEntity.ok(restProducts);
  }
  @GetMapping("/filter")
 public ResponseEntity<Page<RestProduct>> filter(Pageable pageable, @RequestParam("categoryId") UUID categoryId, @RequestParam(value = "productSizes",required = false)List<ProductSize> productSizes){
   FilterQueryBuilder queryBuilder=  FilterQueryBuilder.filterQuery().categoryId(new PublicId(categoryId));

    if(productSizes != null){
    queryBuilder.sizes(productSizes);
    }

  Page<Product> productPage=  applicationService.filter(pageable,queryBuilder.build());
    Page<RestProduct> restProducts=new PageImpl<>(
     productPage.getContent().stream().map(RestProductMapper::toRestDomain).toList(),
      pageable,
      productPage.getTotalElements()
    );

  return ResponseEntity.ok(restProducts);
 }
}
