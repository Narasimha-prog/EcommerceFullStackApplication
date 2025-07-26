package com.lnr.ecom.order.infrastrature.secondary.entity;

import com.lnr.ecom.order.domian.user.aggrigate.Authority;
import com.lnr.ecom.order.domian.user.aggrigate.AuthorityBuilder;
import com.lnr.ecom.order.domian.user.vo.AuthorityName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "authority")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorityEntity implements Serializable {

  @Id
  @Column(name = "name",length = 50)
  @Size(max = 50)
  private String name;



}
