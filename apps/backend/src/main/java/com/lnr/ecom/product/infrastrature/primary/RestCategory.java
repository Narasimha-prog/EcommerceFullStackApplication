package com.lnr.ecom.product.infrastrature.primary;

import com.lnr.ecom.shared.error.domain.Assert;
import org.jilt.Builder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Builder
public record RestCategory(UUID publicId,String name) {

  public RestCategory {
    Assert.notNull("name",name);
    Assert.notNull("publicId",publicId);
  }


}
