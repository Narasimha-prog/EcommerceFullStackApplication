import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { CreateQueryResult } from '@tanstack/angular-query-experimental';
import { OidcSecurityService } from 'angular-auth-oidc-client';
import { ConnectedUser } from '../shared/model/user.model';

@Injectable({
  providedIn: 'root'
})
export class Oauth2Service {
  

  http=inject(HttpClient);
oidcSecurityService=inject(OidcSecurityService);

connectedUserQuery: CreateQueryResult<ConnectedUser> | undefined;

notConnected ="NOT_CONNECTED";

logIn():void {
this.oidcSecurityService.authorize();
}

logOut():void {
  this.oidcSecurityService.logoff().subscribe();
}
initAuthentication(): void {
  this.oidcSecurityService.checkAuth().subscribe((authInfo) => {
   if(authInfo.isAuthenticated) {
     console.log('User is authenticated');
    }
    else {
     console.log('User is not authenticated');
    }
  });

}
}