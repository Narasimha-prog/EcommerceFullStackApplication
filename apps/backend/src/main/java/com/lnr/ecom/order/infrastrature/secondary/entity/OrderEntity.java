package com.lnr.ecom.order.infrastrature.secondary.entity;

import com.lnr.ecom.order.domian.order.vo.OrderStatus;
import com.lnr.ecom.shared.jpa.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jilt.Builder;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "order")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends AbstractAuditingEntity<Long> {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "orderSequenceGenerator")
  @SequenceGenerator(name = "orderSequenceGenerator",sequenceName = "order_sequence",allocationSize = 1)
  @Column(name = "id")
  private Long id;

@Column(name = "public_id",nullable = false,unique = true)
private UUID publicId;

@Enumerated(EnumType.STRING)
@Column(name = "status",nullable = false)
private OrderStatus status;

@Column(name = "stripe_session_id",nullable = false)
private String razorpayId;

@OneToMany(mappedBy = "id.order",cascade = CascadeType.REMOVE)
private Set<OrderedProductEntity>  orderedProducts=new HashSet<>();

@JoinColumn(name = "fk_customer",nullable = false)
@ManyToOne
private UserEntity user;


  @Override
  public boolean equals(Object o) {
    if (!(o instanceof OrderEntity that)) return false;
    return Objects.equals(publicId, that.publicId) && status == that.status && Objects.equals(razorpayId, that.razorpayId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(publicId, status, razorpayId);
  }
}
