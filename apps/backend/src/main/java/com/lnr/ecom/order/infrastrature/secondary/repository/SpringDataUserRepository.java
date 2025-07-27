package com.lnr.ecom.order.infrastrature.secondary.repository;

import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.order.domian.user.repository.UserRepostory;
import com.lnr.ecom.order.domian.user.vo.UserAddress;
import com.lnr.ecom.order.domian.user.vo.UserEmail;
import com.lnr.ecom.order.domian.user.vo.UserPublicId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringDataUserRepository implements UserRepostory {

  private final JpaUserRepository repository;

  @Override
  public void save(User user) {
    if(user.getDbId()!=null){
      repository.findById(user.getDbId());
    }

  }

  @Override
  public Optional<User> get(UserPublicId publicId) {
    return Optional.empty();
  }

  @Override
  public Optional<User> getOneByEmail(UserEmail email) {
    return Optional.empty();
  }

  @Override
  public void updateAddress(UserPublicId publicId, UserAddress address) {

  }
}
