package com.lnr.ecom.order.infrastrature.primary.mapper;

import com.lnr.ecom.order.domian.order.aggrigate.DetailCartItemRequest;
import com.lnr.ecom.order.domian.order.aggrigate.DetailCartItemRequestBuilder;
import com.lnr.ecom.order.infrastrature.primary.order.RestCartItemRequest;
import com.lnr.ecom.order.infrastrature.primary.order.RestCartItemRequestBuilder;
import com.lnr.ecom.product.domain.vo.PublicId;

import java.util.List;

public class DetailCartItemRequestMapper {


  private DetailCartItemRequestMapper(){}

  public static DetailCartItemRequest toDomain(RestCartItemRequest restCartItemRequest){

    return DetailCartItemRequestBuilder.detailCartItemRequest()
      .publicId(new PublicId(restCartItemRequest.publicId()))
      .quentity(restCartItemRequest.quantity())
      .build();

  }

  public static RestCartItemRequest toRest(DetailCartItemRequest detailCartItemRequest){

    return RestCartItemRequestBuilder.restCartItemRequest()
      .publicId(detailCartItemRequest.publicId().value())
      .quantity(detailCartItemRequest.quentity())
      .build();
  }

  public static List<DetailCartItemRequest> toDomainList(List<RestCartItemRequest> restCartItemRequests){
    return restCartItemRequests.stream().map(DetailCartItemRequestMapper::toDomain).toList();
  }

  public static List<RestCartItemRequest> toRestList(List<DetailCartItemRequest> restCartItemRequests){
    return restCartItemRequests.stream().map(DetailCartItemRequestMapper::toRest).toList();
  }
}
