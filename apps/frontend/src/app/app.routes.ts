import { Route } from '@angular/router';
import { AdminCategory } from './admin/category/admin-category/admin-category';
import { CreateCategory } from './admin/category/create-category/create-category';

import { HomeComponenet } from './home/HomeComponenet';
import { AdminProductComponent } from './admin/product/admin-product/admin-product';
import { CreateProductComponent } from './admin/product/create-product/create-product';
import { ProductDetails } from './shop/product-details/product-details';
import { roleCheckGuard } from './auth/role-check-guard';
import { ProductCard } from './hero/product-card';
import { ProductsComponent } from './shop/products/products';
import { CartComponent } from './shop/cart/cartComponent';
import { CartSuccessComponent } from './shop/cart-success/cart-success.componenet';
import { UserOrder } from './user/user-order';
import { AdminOrders } from './admin/admin-orders/admin-orders';
import { CategoryFilter } from './shop/shop-collection/category-filter';


export const appRoutes: Route[] = [
    {
         path: 'admin/categories/list',

         component: AdminCategory,
         canActivate: [roleCheckGuard],
         data: {
            authorities: ['ROLE_ADMIN'],
         }
    },
    {
         path: 'admin/categories/create',

         component: CreateCategory,
         canActivate: [roleCheckGuard],
         data: {
            authorities: ['ROLE_ADMIN'],
         }
    },
    {
         path: 'admin/products/create',

         component: CreateProductComponent,
         canActivate: [roleCheckGuard],
         data: {
            authorities: ['ROLE_ADMIN'],
         }
    },
    {
         path: 'admin/products/list',

         component: AdminProductComponent,
         canActivate: [roleCheckGuard],
         data: {
            authorities: ['ROLE_ADMIN'],
         }
    },
    
    {
        path: '', 
        component: HomeComponenet , 
    },
    {
      path: 'products/:publicId',
      component: ProductDetails
    },
    {
      path:'shop/collection',
      component:CategoryFilter
    },
    
    {
         path:'products',
          component:ProductsComponent
    }
   ,
   {
     path:'cart',
     component:CartComponent
   },
   {
      path:'cart/success',
      component:CartSuccessComponent
   },
   {
      path:'users/orders',
      component:UserOrder
   },
   {
         path: 'admin/orders/list',

         component: AdminOrders,
         canActivate: [roleCheckGuard],
         data: {
            authorities: ['ROLE_ADMIN'],
         }
    },
    
    
];
