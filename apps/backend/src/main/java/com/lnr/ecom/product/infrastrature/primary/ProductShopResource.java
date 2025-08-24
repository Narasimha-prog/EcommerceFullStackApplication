package com.lnr.ecom.product.infrastrature.primary;

import com.lnr.ecom.product.application.ProductsApplicationService;
import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.vo.PublicId;
import com.lnr.ecom.product.infrastrature.primary.mapper.RestProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/products-shop")
@RequiredArgsConstructor
public class ProductShopResource {

private final ProductsApplicationService applicationService;


@GetMapping("/featured")
public ResponseEntity<Page<RestProduct>> getFeatuedProducts(Pageable pageable){

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

}
