import { Component, effect, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartService } from './cart-service';
import { Oauth2Service } from '../../auth/oauth2';
import { Toast } from '../../shared/model/toast/toast';
import { CartItem, CartItemAdd } from '../../shared/model/cart.model';
import { injectQuery } from '@tanstack/angular-query-experimental';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-cart-component',
  imports: [CommonModule],
  templateUrl: './cartComponent.html',
  styleUrl: './cartComponent.scss',
})
export class CartComponent implements OnInit{
  
  



  cartService=inject(CartService);

  oauth2Service=inject(Oauth2Service);

  toastService=inject(Toast);

  
  cart: Array<CartItem>=[];

  labelcheckout="Login to checkout";

  action: 'Login'|'Checkout'='Login';


  constructor(){
   this.extractListToUpdate();
   this.checkUserLoggedIn();
  }

  cartQuery=injectQuery(
    ()=>({
      queryKey:['cart'],
      queryFn:()=>firstValueFrom(this.cartService.getCartDetails())
    }

    )
  )
private extractListToUpdate(){
  effect(()=>{
    if(this.cartQuery.isSuccess()){
      this.cart=this.cartQuery.data().products;
    }
  });
}
ngOnInit(): void {
    this.cartService.addedToCart.subscribe((cart)=>
    this.updateQuantity(cart));
  }

  private updateQuantity(cartUpdated:Array<CartItemAdd>){
        for(const cartItemToUpdate of this.cart){
        const itemToUpdate=  cartUpdated.find((item)=>item.publicId===cartItemToUpdate.publicId);
          if(itemToUpdate){
            cartItemToUpdate.quantity=itemToUpdate.quantity;
          }else{
            this.cart.splice(this.cart.indexOf(cartItemToUpdate),1);
          }
        }
  }
  addQuentyToCart(publicId:string){
    this.cartService.addToCart(publicId,'add');
  }
    removeQuentyToCart(publicId:string){
    this.cartService.addToCart(publicId,'remove');
  }

  removeItem(publicId:string){
   const itemToRemoveIndex= this.cart.findIndex(item=>item.publicId===publicId)
   if(itemToRemoveIndex){
        this.cart.splice(itemToRemoveIndex,1);
   }
   this.cartService.removeFromCart(publicId);
  }

  computeTotal(){
    return this.cart.reduce((acc,item)=>acc+item.price*item.quantity,0);
  }

  checkUserLoggedIn(){
const connectedUserQuery=this.oauth2Service.connectedUserQuery;
if(connectedUserQuery?.isError){
  this.labelcheckout='Login to checkout';
  this.action='Login';
}else if(connectedUserQuery?.isSuccess()){
  this.labelcheckout='checkout';
  this.action='Checkout';
}
  }
}
