package com.lnr.ecom.product.infrastrature.primary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lnr.ecom.product.application.ProductsApplicationService;
import com.lnr.ecom.product.domain.aggregate.Category;
import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.vo.PublicId;
import com.lnr.ecom.product.infrastrature.primary.exception.MultipartPictureException;
import com.lnr.ecom.product.infrastrature.primary.mapper.RestCategoryMapper;
import com.lnr.ecom.product.infrastrature.primary.mapper.RestProductMapper;
import com.lnr.ecom.product.infrastrature.seconadary.entity.mapper.ProductEntityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductAdminResource {

  private final ProductsApplicationService productApplicationService;

  public static final String ROLE_ADMIN="ROLE_ADMIN";

  private final ObjectMapper objectMapper=new ObjectMapper();


@PreAuthorize("hasAnyRole('"+ROLE_ADMIN+"')")
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<RestProduct> save(MultipartHttpServletRequest request, @RequestPart("dto") String productRaw) throws JsonProcessingException {

   List<RestPitchure> restPitchures= request.getFileMap()
    .values()
    .stream()
     .map(mapMultiPartFiletoRestPitcures())
      .toList();
 RestProduct restProduct=  objectMapper.readValue(productRaw,RestProduct.class);
 restProduct.addPictureAttachment(restPitchures);
 Product newProduct= RestProductMapper.toDomain(restProduct);
 Product product= productApplicationService.createProduct(newProduct);

 return ResponseEntity.ok(RestProductMapper.toRestDomain(product));
  }


  @DeleteMapping()
  @PreAuthorize("hasAnyRole('"+ROLE_ADMIN+"')")
  public ResponseEntity<UUID> delete(@RequestParam("publicId") UUID id){
  try{
    PublicId publicId= productApplicationService.deleteProduct(new PublicId(id));
    return ResponseEntity.ok(publicId.value());
  }catch (EntityNotFoundException e){
    log.error("Could not delete the Product with id {}",id,e);

    ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    return ResponseEntity.of(problemDetail).build();
  }

  }




  @GetMapping
  @PreAuthorize("hasAnyRole('"+ROLE_ADMIN+"')")
  public ResponseEntity<Page<RestProduct>> getAll(Pageable pageable){

    Page<Product> productPage= productApplicationService.findAllProducts(pageable);
    Page<RestProduct> restCategoryPage=    new PageImpl<>(
      productPage.getContent().stream().map(RestProductMapper::toRestDomain).toList(),
      pageable
      ,productPage.getTotalElements()

    );
    return ResponseEntity.ok(restCategoryPage);

  }



  private Function<MultipartFile,RestPitchure> mapMultiPartFiletoRestPitcures() {

  return multipartFile -> {
    try {

      return new RestPitchure(multipartFile.getBytes(),multipartFile.getContentType());
    } catch (IOException e) {
      throw new MultipartPictureException( String.format("Cannot Parse MultiPart File : %s",multipartFile.getOriginalFilename()));
    }
  };

  }
}
