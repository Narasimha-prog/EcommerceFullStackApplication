package com.lnr.ecom.product.infrastrature.primary;

import com.lnr.ecom.product.application.ProductsApplicationService;
import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.infrastrature.primary.mapper.RestProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
