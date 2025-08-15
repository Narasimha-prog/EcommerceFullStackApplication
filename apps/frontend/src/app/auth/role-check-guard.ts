import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from "@angular/router";
import { Oauth2Service } from "./oauth2";
import { inject, PLATFORM_ID } from "@angular/core";
import { isPlatformBrowser } from "@angular/common";
import { catchError, filter, interval, map, of, timeout } from "rxjs";

export const roleGuardCheck: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    const oauth2Service = inject(Oauth2Service);
    const platformId = inject(PLATFORM_ID);

    const authorities = next.data['authorities'];
       const router = inject(Router);
    // console.log('[RoleGuard] Route:', state.url);
    // console.log('[RoleGuard] Required authorities:', authorities);

    if (isPlatformBrowser(platformId)) {
        console.log('[RoleGuard] Running on browser');

        return interval(50)
            .pipe(
                filter(() => {
                    const pending = oauth2Service.connectedUserQuery?.isPending();
                    // console.log('[RoleGuard] User data pending:', pending);
                    return pending === false;
                }),
                map(() => {
                    const userData = oauth2Service.connectedUserQuery?.data();
                    // console.log('[RoleGuard] User data:', userData);
                    return userData;
                }),
                map(data => {
                    const allowed = !authorities || authorities.length === 0 || oauth2Service.hasAnyAuthorities(data!, authorities);
                    // console.log('[RoleGuard] Access allowed:', allowed);
                    if (!allowed) {
                    router.navigate(['/']); // redirect if unauthorized
                }
                return allowed;
                }),
                timeout(3000),
                catchError(() => {
                router.navigate(['/']); // redirect on timeout
                return of(false);
            })
            );
    } else {
         console.log('[RoleGuard] Running on server — allowing access for SSR');
        return true; // Allow server rendering
    }
};
