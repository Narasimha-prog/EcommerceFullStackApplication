package com.lnr.ecom.product.infrastrature.primary;

import com.lnr.ecom.product.application.ProductsApplicationService;
import com.lnr.ecom.product.domain.aggregate.Category;
import com.lnr.ecom.product.domain.vo.PublicId;
import com.lnr.ecom.product.infrastrature.primary.mapper.RestCategoryMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.lnr.ecom.product.infrastrature.primary.ProductAdminResource.ROLE_ADMIN;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryResource {

  private final ProductsApplicationService applicationService;


  @PostMapping
  @PreAuthorize("hasAnyRole('"+ROLE_ADMIN+"')")
  public ResponseEntity<RestCategory> save(@RequestBody RestCategory restCategory){

    Category newCategory= RestCategoryMapper.toDomin(restCategory);

   Category savedCategory= applicationService.createCategory(newCategory);

   return ResponseEntity.ok(RestCategoryMapper.toRestDomain(savedCategory));

  }

  @DeleteMapping
  @PreAuthorize("hasAnyRole('"+ROLE_ADMIN+"')")
  public ResponseEntity<UUID> delete(UUID publicId){
    try{
      PublicId deleteCategoryPublicId=  applicationService.deleteCategory(new PublicId(publicId));
      return ResponseEntity.ok(deleteCategoryPublicId.value());
    }catch (EntityNotFoundException e){
      log.error("Could not delete Category with Id : {}",publicId,e);
      ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
      return ResponseEntity.of(problemDetail).build();
    }

  }
}
