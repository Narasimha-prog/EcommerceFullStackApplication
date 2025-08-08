package com.lnr.ecom.product.infrastrature.primary;

import com.lnr.ecom.shared.error.domain.Assert;
import org.jilt.Builder;

@Builder
public record RestPitchure(byte[] file, String mineType) {

  public RestPitchure {
    Assert.notNull("file",file);
    Assert.notNull("MineTYpe",mineType);
  }


}
