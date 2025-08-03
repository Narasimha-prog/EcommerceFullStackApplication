package com.lnr.ecom.order.domian.user.aggrigate;

import com.lnr.ecom.order.domian.user.vo.*;
import com.lnr.ecom.shared.error.domain.Assert;
import lombok.Getter;
import org.jilt.Builder;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
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

  public User(UserFirstName firstName, UserLastName lastName, UserEmail email, UserPublicId publicId, UserImageUrl imageUrl, Instant lastModifInstant, Instant createdInstant, Set<Authority> authorities, Long dbId, UserAddress address, Instant lastSeenDate) {
    assertMandatoryField(firstName,lastName,email,authorities);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.publicId = publicId;
    this.imageUrl = imageUrl;
    this.lastModifInstant = lastModifInstant;
    this.createdInstant = createdInstant;
    this.authorities = authorities;
    this.dbId = dbId;
    this.address = address;
    this.lastSeenDate = lastSeenDate;
  }

  private void assertMandatoryField(UserFirstName firstName,UserLastName lastName,UserEmail email,Set<Authority> authorities){
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
    this.lastSeenDate = user.lastSeenDate;
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
      try {
        userBuilder.lastSeenDate(Instant.parse(attributes.get("last_signed_in").toString()));
      } catch (Exception e) {
        userBuilder.lastSeenDate(Instant.now());
      }
    } else {
      userBuilder.lastSeenDate(Instant.now());
    }

  Set<Authority> authorities=  rolesFromAuthorities
      .stream()
      .map(authority-> AuthorityBuilder.authority().name(new AuthorityName(authority)).build())
      .collect(Collectors.toSet());

    userBuilder.authorities(authorities);


    return userBuilder.build();

  }

}
