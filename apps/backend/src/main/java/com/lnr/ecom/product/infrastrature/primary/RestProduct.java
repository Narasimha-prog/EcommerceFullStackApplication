package com.lnr.ecom.product.infrastrature.primary;

import com.lnr.ecom.product.domain.vo.ProductSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jilt.Builder;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Data
public class RestProduct {


  private String brand;

  private String color;

  private String description;

  private String name;

  private double price;

  private ProductSize size;


  private RestCategory category;

  private boolean featured;

  private List<RestPitchure> pitchureList;

  private UUID publicId;

  private int nbInStock;


public void addPictureAttachment(List<RestPitchure> restPitchures){
  this.pitchureList.addAll(restPitchures);
}


}
