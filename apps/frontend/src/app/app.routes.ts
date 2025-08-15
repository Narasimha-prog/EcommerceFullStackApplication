import { Route } from '@angular/router';
import { AdminCategory } from './admin/category/admin-category/admin-category';
import { CreateCategory } from './admin/category/create-category/create-category';
import { roleGuardCheck } from './auth/role-check-guard';
import { HomeComponenet } from './home/HomeComponenet';


export const appRoutes: Route[] = [
    {
         path: 'admin/categories/list',

         component: AdminCategory,
         canActivate: [roleGuardCheck],
         data: {
            authorities: ['ROLE_ADMIN'],
         }
    },
    {
         path: 'admin/categories/create',

         component: CreateCategory,
         canActivate: [roleGuardCheck],
         data: {
            authorities: ['ROLE_ADMIN'],
         }
    },
    
    {
        path: '', 
        component: HomeComponenet , 
    }
   
];
