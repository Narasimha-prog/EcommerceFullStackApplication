package com.lnr.ecom.product.infrastrature.seconadary.entity;

import com.lnr.ecom.shared.jpa.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.*;
import org.jilt.Builder;

import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = false)
@Entity
@Table(name = "product_category")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity extends AbstractAuditingEntity<Long> {

  @Id
  @SequenceGenerator(name = "category_sequences",sequenceName = "product_category_sequence",allocationSize = 1)
  @GeneratedValue(generator ="category_sequences",strategy = GenerationType.SEQUENCE)
  @Column(name = "id",nullable = false)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(name = "name",nullable = false)
private String name;

@Column(name = "public_id",nullable = false,unique = true)
  private UUID publicId;


@OneToMany(mappedBy = "category")
private Set<ProductEntity> products;

}
