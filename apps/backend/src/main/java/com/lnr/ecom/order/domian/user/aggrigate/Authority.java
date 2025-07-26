package com.lnr.ecom.order.domian.user.aggrigate;

import com.lnr.ecom.order.domian.user.vo.AuthorityName;
import com.lnr.ecom.shared.error.domain.Assert;
import lombok.Getter;
import org.jilt.Builder;

@Builder
@Getter
public class Authority {

private AuthorityName authorityName;

  public Authority(AuthorityName authorityName) {
    Assert.notNull("AuthorityNotNull",authorityName);
    this.authorityName = authorityName;
  }
}
