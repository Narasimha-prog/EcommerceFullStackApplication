package com.lnr.ecom.product.domain.infrastrature.seconadary.entity;

import com.lnr.ecom.product.domain.vo.ProductSize;
import com.lnr.ecom.shared.jpa.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jilt.Builder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = false,onlyExplicitlyIncluded = true)
@Entity
@Builder
@Data
@AllArgsConstructor
@Table(name = "product")
public class ProductEnity extends AbstractAuditingEntity<Long> {

@Id
@SequenceGenerator(name = "product_seq",sequenceName = "product_sequence")
@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_seq")
@Column(nullable = false)
@EqualsAndHashCode.Include
  private Long id;


@Column(name = "brand")
private String brand;


  @Column(name = "price")
  private Double price;

  @Column(name = "size")
  @Enumerated(EnumType.STRING)
  private ProductSize size;


  @Column(name = "description")
  private String description;

  @Column(name = "featured")
  private Boolean featured;



  @Column(name = "nb_in_stock")
  private Integer nbInStock;


  @Column(name = "public_id",unique = true)
  private UUID publicId;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  private Set<PictureEntity> pictureEntities=new HashSet<>();

}
