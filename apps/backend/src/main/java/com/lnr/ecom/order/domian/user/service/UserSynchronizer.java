package com.lnr.ecom.order.domian.user.service;

import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.order.domian.user.repository.UserRepostory;
import com.lnr.ecom.order.domian.user.vo.UserAddressToUpdate;
import com.lnr.ecom.order.infrastrature.secondary.service.kinde.KindeService;

import com.lnr.ecom.shared.authentication.application.AuthenticatedUser;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class UserSynchronizer {

  private final UserRepostory userRepostory;

  private final KindeService  kindeService;

  private static final String UPDATE_AT_KEY="last_signed_in";

  public UserSynchronizer(UserRepostory userRepostory, KindeService kindeService) {
    this.userRepostory = userRepostory;
    this.kindeService = kindeService;
  }

  public  void syncWithId(Jwt jwtToken, boolean forceResyn){
   Map<String,Object> claims=jwtToken.getClaims();
   List<String> rolesFromToken=  AuthenticatedUser.extractRolesFromToken(jwtToken);

   Map<String,Object> userInfo= kindeService.getUserInfo(claims.get("sub").toString());
   User user= User.fromTokenAttributes(userInfo,rolesFromToken);


    Optional<User> existingUer= userRepostory.getOneByEmail(user.getEmail());
    if(existingUer.isPresent()){
     Instant lastModifiedDate= existingUer.orElseThrow().getLastModifInstant();
     Instant idpModifiedDate=Instant.ofEpochSecond((Long)claims.get(UPDATE_AT_KEY));

     if(idpModifiedDate.isAfter(lastModifiedDate) || forceResyn){
           updateUser(user,existingUer.get());
     }
    }else{
      user.intiFieldsForSignUp();
      userRepostory.save(user);

    }

  }

  private void updateUser(User user, User existingUser) {
    existingUser.updateFromUser(user);
    userRepostory.save(existingUser);
  }

  public void updateAddress(UserAddressToUpdate userAddressToUpdate){
    userRepostory.updateAddress(userAddressToUpdate.userPublicId(),userAddressToUpdate.userAddress());

  }
}
