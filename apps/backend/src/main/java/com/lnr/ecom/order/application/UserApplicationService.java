package com.lnr.ecom.order.application;

import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.order.domian.user.repository.UserRepostory;
import com.lnr.ecom.order.domian.user.service.UserReader;
import com.lnr.ecom.order.domian.user.service.UserSynchronizer;
import com.lnr.ecom.order.domian.user.vo.UserAddressToUpdate;
import com.lnr.ecom.order.domian.user.vo.UserEmail;
import com.lnr.ecom.order.infrastrature.secondary.service.kinde.KindeService;
import com.lnr.ecom.shared.authentication.application.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class UserApplicationService {

  private final UserSynchronizer userSynchronizer;
  private final UserReader userReader;

  public UserApplicationService(UserRepostory userRepostory, KindeService kindeService) {
    this.userSynchronizer = new UserSynchronizer(userRepostory, kindeService);
    this.userReader = new UserReader(userRepostory);
  }

  @Transactional
  public User getAuthenticatedUserWithSync(Jwt token, boolean forceResync) {
    userSynchronizer.syncWithId(token, forceResync);

    return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get())).orElseThrow();
  }

  @Transactional(readOnly = true)
  public User getAuthenticatedUser() {
    return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get())).orElseThrow();
  }


  @Transactional
  public void updateUserAddress(UserAddressToUpdate userAddressToUpdate){
      userSynchronizer.updateAddress(userAddressToUpdate);
  }
}
