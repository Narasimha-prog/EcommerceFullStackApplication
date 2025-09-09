package com.lnr.ecom.order.infrastrature.primary;

import com.lnr.ecom.order.application.UserApplicationService;
import com.lnr.ecom.order.domian.user.aggrigate.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserResource {

  private final UserApplicationService userApplicationService;

  @GetMapping("/authenticated")
  public ResponseEntity<RestUser> getAuthenticatedUser(
    @AuthenticationPrincipal Jwt jwtToken,
    @RequestParam boolean forceReSyn
    ){

User authenticatedUserWithSync =userApplicationService.getAuthenticatedUserWithSync(jwtToken,forceReSyn);

RestUser restUser=RestUser.from(authenticatedUserWithSync);
return ResponseEntity.ok(restUser);

  }




}
