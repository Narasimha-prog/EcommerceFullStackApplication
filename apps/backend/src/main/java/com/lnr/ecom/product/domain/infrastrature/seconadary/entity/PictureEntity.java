package com.lnr.ecom.product.domain.infrastrature.seconadary.entity;

import com.lnr.ecom.shared.jpa.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jilt.Builder;

import java.util.Objects;


@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = false)
@Entity
@Table(name = "product_picture")
@Builder
@Data
@AllArgsConstructor
public class PictureEntity  extends AbstractAuditingEntity<Long> {
  @Id
  @SequenceGenerator(name = "picture_seq",sequenceName = "product_picture_sequence",allocationSize = 1)
  @Column(name = "id",nullable = false)
  @EqualsAndHashCode.Include
  private Long id;

  @Lob
  @Column(name = "file",nullable = false)
  private byte[] file;

  @Column(name = "file_content_type",nullable = false)
  private String mineType;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_fk", nullable = false)
  private ProductEnity product;

  @ManyToOne
  @JoinColumn(name = "category_fk", referencedColumnName = "id")
  private CategoryEntity category;

}


