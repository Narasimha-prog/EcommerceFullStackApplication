package com.lnr.ecom.product.domain.infrastrature.seconadary.entity.mapper;

import com.lnr.ecom.product.domain.aggregate.Picture;
import com.lnr.ecom.product.domain.aggregate.PictureBuilder;
import com.lnr.ecom.product.domain.infrastrature.seconadary.entity.PictureEntity;
import com.lnr.ecom.product.domain.infrastrature.seconadary.entity.PictureEntityBuilder;

import java.util.List;


public final class PictureEntityMapper {

  private PictureEntityMapper() {

  }

  public static PictureEntity toEntity(Picture picture){
    return PictureEntityBuilder.pictureEntity()
      .file(picture.file())
      .mineType(picture.mineType())
      .build();
  }

  public static Picture toDomain(PictureEntity pictureEntity){
    return PictureBuilder.picture()
      .file(pictureEntity.getFile())
      .mineType(pictureEntity.getMineType())
      .build();
  }

  public static  List<Picture> toDomainList(List<PictureEntity> pictureEntities){
    return pictureEntities.stream().map(PictureEntityMapper::toDomain).toList();
  }

  public static  List<PictureEntity> toEntityList(List<Picture> picture){
    return picture.stream().map(PictureEntityMapper::toEntity).toList();
  }





}
