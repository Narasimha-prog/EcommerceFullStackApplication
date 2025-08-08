package com.lnr.ecom.product.infrastrature.primary.mapper;

import com.lnr.ecom.product.domain.aggregate.Picture;
import com.lnr.ecom.product.domain.aggregate.PictureBuilder;
import com.lnr.ecom.product.infrastrature.primary.RestPitchure;
import com.lnr.ecom.product.infrastrature.primary.RestPitchureBuilder;

import java.util.List;

public final class RestPictureMapper {

  private RestPictureMapper(){}

  public static Picture toDomain(RestPitchure restPitchure){

    return PictureBuilder.picture()
      .file(restPitchure.file())
      .mineType(restPitchure.mineType())
      .build();
  }

  public static RestPitchure toRestDomain(Picture picture){
    return RestPitchureBuilder.restPitchure()
      .file(picture.file())
      .mineType(picture.mineType())
      .build();
  }

  public static List<Picture> toDomianList(List<RestPitchure> restPitchures){
    return restPitchures.stream().map(RestPictureMapper::toDomain).toList();
  }

  public  static  List<RestPitchure> toRestDomainList(List<Picture> pictures){
    return  pictures.stream().map(RestPictureMapper::toRestDomain).toList();
  }
}
