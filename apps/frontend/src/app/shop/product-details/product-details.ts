import { Component, effect, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { injectParams } from 'ngxtension/inject-params'
import { UserProductService } from '../../shared/service/user-product';
import { Router } from '@angular/router';
import { Toast } from '../../shared/model/toast/toast';
import { Pagination } from '../../shared/model/request.model';
import { injectQuery } from '@tanstack/angular-query-experimental';
import { firstValueFrom } from 'rxjs';
import { FaIconComponent, FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { ProductCard } from "../../hero/product-card";
import { App } from '../../app';


@Component({
  selector: 'app-product-details',
  imports: [CommonModule, FaIconComponent, FontAwesomeModule, ProductCard],
  templateUrl: './product-details.html',
  styleUrl: './product-details.scss',
})
export class ProductDetails {

  publicId=injectParams('publicId');

  productService=inject(UserProductService);

  router=inject(Router);

  toastService = inject(Toast);

  lastPublicId='';

  pageRequest:Pagination={
    page:0,
    size:20,
    sort:[]
   }

   productQuery=injectQuery(()=>(
    {
      queryKey:['product',this.publicId],
      queryFn:()=> firstValueFrom( this.productService.findOneProduct(this.publicId()!))
    }
   ));

   relatedProductsQuery=injectQuery(()=>(
    {
      queryKey:['product',this.publicId,this.pageRequest],
      queryFn:()=> firstValueFrom( this.productService.findProductsRelatedToCategory(this.pageRequest,this.publicId()!))
    }
   ));
constructor(){
  effect(()=>{
    this.handlePublicIdChange();
  })
  effect(()=>{
   this.handleProductQueryError();
  })
  effect(()=>{
    this.handleRelatedProductsQueryError();
   })
}

   handlePublicIdChange(){
    if(this.publicId()){
      if(this.lastPublicId!=this.publicId() && this.lastPublicId!==''){
       
       this.relatedProductsQuery.refetch();
       this.productQuery.refetch();
      }
      this.lastPublicId=this.publicId()!;
    }
   }

   private handleProductQueryError(){
    if (this.productQuery.isError()) {
      this.toastService.show('Error while fetching product details, Please try again', 'ERROR');
      
    }
   }

   private handleRelatedProductsQueryError(){
    if (this.relatedProductsQuery.isError()) {
      this.toastService.show('Error while fetching products, Please try again', 'ERROR');
    }
   }

  }
  




