package com.lnr.ecom.product.domain.aggregate;

import com.lnr.ecom.shared.error.domain.Assert;
import org.jilt.Builder;

@Builder
public record Picture(byte[] file, String mineType) {
  public Picture {
    Assert.notNull("file",file);
    Assert.notNull("file",mineType);
  }
}
