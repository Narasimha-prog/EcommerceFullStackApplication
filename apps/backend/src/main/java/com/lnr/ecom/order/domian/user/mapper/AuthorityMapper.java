package com.lnr.ecom.order.domian.user.mapper;

import com.lnr.ecom.order.domian.user.aggrigate.Authority;
import com.lnr.ecom.order.domian.user.aggrigate.AuthorityBuilder;
import com.lnr.ecom.order.domian.user.vo.AuthorityName;
import com.lnr.ecom.order.infrastrature.secondary.entity.AuthorityEntity;

import java.util.Set;
import java.util.stream.Collectors;

public final class AuthorityMapper {

  private AuthorityMapper(){

  }
  public static Set<AuthorityEntity> toEntities(Set<Authority> authorities){
    return authorities
      .stream()
      .map(authority -> AuthorityEntity
        .builder()
        .name(authority.getName().name())
        .build()
      ).collect(Collectors.toSet());
  }

  public static Set<Authority> toDomain(Set<AuthorityEntity> authorityEntities){
    return authorityEntities
      .stream()
      .map(authorityEntity -> AuthorityBuilder.authority().name(new AuthorityName(authorityEntity.getName())).build())
      .collect(Collectors.toSet());
  }
}
