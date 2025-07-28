package com.lnr.ecom.order.infrastrature.primary;

import com.lnr.ecom.order.domian.user.aggrigate.Authority;
import org.jilt.Builder;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record RestAuthority(String name) {

  public static Set<String>  fromSet(Set<Authority> authorities){
    return authorities.stream()
      .map(authority -> authority.getName().name())
      .collect(Collectors.toSet());
  }
}
