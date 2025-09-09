package com.lnr.ecom.order.domian.user.mapper;

import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.order.domian.user.aggrigate.UserBuilder;
import com.lnr.ecom.order.domian.user.vo.*;
import com.lnr.ecom.order.infrastrature.secondary.entity.UserEntity;
import com.lnr.ecom.order.infrastrature.secondary.entity.UserEntityBuilder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class UserMapper {

  private UserMapper(){

  }
  public static UserEntity toEntity(User user){
    UserEntityBuilder entityBuilder=new UserEntityBuilder();


    if(user.getFirstName()!= null){
      entityBuilder.firstName(user.getFirstName().userFirstName());
    }

    if(user.getLastName()!= null){
      entityBuilder.lastName(user.getLastName().userLastName());
    }

    if(user.getPublicId()!= null){
      entityBuilder.publicId(user.getPublicId().value());
    }

    if(user.getImageUrl()!= null){
      entityBuilder.imageUrl(user.getImageUrl().imageUrl());
    }

    if(user.getAddress()!=null){
      entityBuilder.addressCity(user.getAddress().city());
      entityBuilder.addressStreet(user.getAddress().street());
      entityBuilder.addressCountry(user.getAddress().cuntry());
      entityBuilder.addressZipCode(user.getAddress().zipCode());
    }
    return entityBuilder
      .authorities(AuthorityMapper. toEntities(user.getAuthorities()))
      .email(user.getEmail().email())
      .lastSeen(user.getLastSeenDate())
      .id(user.getDbId())
      .build();
  }

  public static User toDomain(UserEntity userEntity){
    UserBuilder userBuilder=UserBuilder.user();
    if(userEntity.getImageUrl()!= null){
      userBuilder.imageUrl(new UserImageUrl(userEntity.getImageUrl()));
    }

    if(userEntity.getAddressStreet()!= null){
      userBuilder.address(
        UserAddressBuilder.userAddress()
          .city(userEntity.getAddressCity())
          .street(userEntity.getAddressStreet())
          .cuntry(userEntity.getAddressCountry())
          .zipCode(userEntity.getAddressZipCode())
          .build()
      );
    }


    return userBuilder
      .firstName(new UserFirstName(userEntity.getFirstName()))
      .lastName(new UserLastName(userEntity.getLastName()))
      .email(new UserEmail(userEntity.getEmail()))
      .authorities(AuthorityMapper.toDomain(userEntity.getAuthorities()))
      .publicId(new UserPublicId(userEntity.getPublicId()))
      .lastModifInstant(userEntity.getLastModifiedDate())
      .createdInstant(userEntity.getCreatedDate())
      .dbId(userEntity.getId())
      .build();


  }
  public static Set<UserEntity> toEntityList(List<User> userList){
    return userList.stream().map(UserMapper::toEntity).collect(Collectors.toSet());
  }
  public static Set<User> toDomainList(List<UserEntity> userList){
    return userList.stream().map(UserMapper::toDomain).collect(Collectors.toSet());
  }
}
