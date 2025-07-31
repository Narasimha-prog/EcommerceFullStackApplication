import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { CreateQueryResult } from '@tanstack/angular-query-experimental';
import { OidcSecurityService } from 'angular-auth-oidc-client';

@Injectable({
  providedIn: 'root'
})
export class Oauth2Service {
  

  http=inject(HttpClient);
oidcSecurityService=inject(OidcSecurityService);

connectedUserQuery: CreateQueryResult<ConnectedUser> | undefined;

notConnected ="NOT_CONNECTED";

}
