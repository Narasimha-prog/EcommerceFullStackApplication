package com.lnr.ecom.order.domian.user.repository;

import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.order.domian.user.vo.UserAddress;
import com.lnr.ecom.order.domian.user.vo.UserEmail;
import com.lnr.ecom.order.domian.user.vo.UserPublicId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepostory {

  void save(User user);

  Optional<User> get(UserPublicId publicId);

  Optional<User> getOneByEmail(UserEmail email);

  void updateAddress(UserPublicId publicId, UserAddress address);


}
