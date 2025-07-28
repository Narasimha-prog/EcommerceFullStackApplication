package com.lnr.ecom.order.domian.user.service;

import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.order.domian.user.repository.UserRepostory;
import com.lnr.ecom.order.domian.user.vo.UserEmail;
import com.lnr.ecom.order.domian.user.vo.UserPublicId;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserReader {

  private final UserRepostory  repostory;

  public Optional<User> getByEmail(UserEmail email){
  return repostory.getOneByEmail(email);
  }

  public Optional<User> getByPublicId(UserPublicId publicId){
    return repostory.get(publicId);
  }
}
