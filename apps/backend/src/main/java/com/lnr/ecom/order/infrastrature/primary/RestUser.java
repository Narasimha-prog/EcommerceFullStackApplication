package com.lnr.ecom.order.infrastrature.primary;

import com.lnr.ecom.order.domian.user.aggrigate.Authority;
import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.order.domian.user.vo.UserFirstName;
import org.jilt.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record RestUser(
  UUID publicId,
  String firstName,
  String lastName,
  String email,
  String imageUrl,
  Set<String> authorities
) {

  public static RestUser from(User user){

    RestUserBuilder restUserBuilder=RestUserBuilder.restUser();
    if(user.getImageUrl() != null){
      restUserBuilder.imageUrl(user.getImageUrl().imageUrl());
    }
 return   restUserBuilder.firstName(user.getFirstName().userFirstName())
   .lastName(user.getLastName().userLastName())
   .email(user.getEmail().email())
   .publicId(user.getPublicId().userPublicId())
   .authorities(RestAuthority.fromSet(user.getAuthorities()))
   .build();
  }
}
