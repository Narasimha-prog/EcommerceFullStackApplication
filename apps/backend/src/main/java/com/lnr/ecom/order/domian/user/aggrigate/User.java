package com.lnr.ecom.order.domian.user.aggrigate;

import com.lnr.ecom.order.domian.user.vo.*;
import com.lnr.ecom.shared.error.domain.Assert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jilt.Builder;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Getter
public class User {

  private UserFirstName firstName;

  private UserLastName lastName;

  private UserEmail email;

  private UserPublicId publicId;

  private UserImageUrl imageUrl;

  private Instant lastModifInstant;

  private Instant createdInstant;

  private Set<Authority>  authorities;

  private Long dbId;

  private UserAddress address;

  private Instant lastSeenDate;

  private void assertMandatoryField(){
    Assert.notNull("FirstName",firstName);
    Assert.notNull("LastName",lastName);
    Assert.notNull("Email",email);
    Assert.notNull("Authority",authorities);
  }

  public void updateFromUser(User user){
    this.email=user.email;
    this.firstName=user.firstName;
    this.lastName=user.lastName;
    this.imageUrl=user.imageUrl;
  }

  public void intiFieldsForSignUp(){
    this.publicId= new UserPublicId(UUID.randomUUID());
  }

  public static User fromTokenAttributes(Map<String,Object> attributes, List<String> rolesFromAuthorities ) {
    UserBuilder userBuilder = new UserBuilder();
    if (attributes.containsKey("preferred_email")) {
      userBuilder.email(new UserEmail(attributes.get("preferred_email").toString()));
    }
    if (attributes.containsKey("last_name")) {
      userBuilder.lastName(new UserLastName(attributes.get("last_name").toString()));
    }
    if (attributes.containsKey("first_name")) {
      userBuilder.firstName(new UserFirstName(attributes.get("first_name").toString()));
    }
    if (attributes.containsKey("picture")) {
      userBuilder.imageUrl(new UserImageUrl(attributes.get("picture").toString()));
    }

    if (attributes.containsKey("last_signed_in")) {
      userBuilder.lastSeenDate(Instant.parse(attributes.get("last_signed_in").toString()));
    }

  Set<Authority> authorities=  rolesFromAuthorities
      .stream()
      .map(authority-> AuthorityBuilder.authority().name(new AuthorityName(authority)).build())
      .collect(Collectors.toSet());

    userBuilder.authorities(authorities);


    return userBuilder.build();

  }

}
