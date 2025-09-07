import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { CreateQueryResult, injectQuery } from '@tanstack/angular-query-experimental';
import { OidcSecurityService } from 'angular-auth-oidc-client';
import { ConnectedUser } from '../shared/model/user.model';
import { firstValueFrom, Observable } from 'rxjs';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class Oauth2Service {
  

http=inject(HttpClient);

oidcSecurityService=inject(OidcSecurityService);

connectedUserQuery: CreateQueryResult<ConnectedUser> | undefined;

notConnected ="NOT_CONNECTED";


fetch(): CreateQueryResult<ConnectedUser> {
  return injectQuery(() => ({
    queryKey: ['connectedUser'],
    queryFn: () => firstValueFrom(this.fetchUserHttp(false)),
  }));
 
}

fetchUserHttp(forceReSync: boolean): Observable<ConnectedUser> {
  const param=new HttpParams().set('forceReSyn',forceReSync)
  return this.http.get<ConnectedUser>(`${environment.apiUrl}/users/authenticated`, {params:param});
}

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

hasAnyAuthorities(connectedUser: ConnectedUser, authorities: string[] | string): boolean {
 if(!Array.isArray(authorities)) {
    authorities = [authorities];
 }
 if(connectedUser.authorities){
    return connectedUser.authorities.some((authority:string)=> authorities.includes(authority));
 }else{
  return false;
 }
}
}