package com.lnr.ecom.order.infrastrature.secondary.repository;


import com.lnr.ecom.order.infrastrature.secondary.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserEntity,Long> {

  Optional<UserEntity> findByEmail(String email);

  List<UserEntity> findByPublicIdIn(List<UUID> publicIds);

  Optional<UserEntity> findOneByPublicId(UUID publicId);

  @Modifying
  @Query("UPDATE UserEntity u " +
    "SET u.addressStreet = :street, " +
    "u.addressCity = :city, " +
    "u.addressZipCode = :zipCode, " +
    "u.addressCountry = :country " +
    "WHERE u.publicId = :userPublicId")
  void updateAddress(UUID userPublicId, String street, String city, String zipCode, String country);

}
