package com.lnr.ecom.order.infrastrature.secondary.entity;

import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.order.domian.user.aggrigate.UserBuilder;
import com.lnr.ecom.order.domian.user.vo.*;
import com.lnr.ecom.shared.jpa.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jilt.Builder;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "ecommerce_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity extends AbstractAuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userSequenceGenerator")
  @SequenceGenerator(name = "userSequenceGenerator",sequenceName = "user_sequence",allocationSize = 1)
  @Column(name = "id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "image_url")
  private String imageUrl;


  @Column(name = "public_id")
  private UUID publicId;


  @Column(name = "address_street")
  private String addressStreet;

  @Column(name = "address_city")
  private String addressCity;

  @Column(name="address_zip_code")
  private String addressZipCode;

  @Column(name = "address_country")
  private String addressCountry;

  @Column(name = "last_seen")
  private Instant lastSeen;

  @ManyToMany(cascade = CascadeType.REMOVE)
  @JoinTable(
    name = "user_authority",
    joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "authority_name",referencedColumnName = "name")}
  )
  private Set<AuthorityEntity> authorities=new HashSet<>();


  public void updateFromUser(User user){

    this.firstName=user.getFirstName().userFirstName();
    this.lastName=user.getLastName().userLastName();
    this.email=user.getEmail().email();
    this.imageUrl=user.getImageUrl().imageUrl();
    this.lastSeen=user.getLastSeenDate();

  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof UserEntity that)) return false;
    return Objects.equals(publicId, that.publicId);
  }


}
