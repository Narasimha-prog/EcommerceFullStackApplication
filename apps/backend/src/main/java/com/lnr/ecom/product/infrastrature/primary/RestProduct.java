package com.lnr.ecom.product.infrastrature.primary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lnr.ecom.product.domain.vo.ProductSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jilt.Builder;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
  @JsonProperty("pictures")
  private List<RestPitchure> pictures;

  private UUID publicId;

  private int nbInStock;


public void addPictureAttachment(List<RestPitchure> restPitchures){
  this.pictures.addAll(restPitchures);
}


}
