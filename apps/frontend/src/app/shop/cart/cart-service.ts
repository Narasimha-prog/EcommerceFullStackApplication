import { HttpClient } from '@angular/common/http';
import { inject, Injectable, PLATFORM_ID } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { CartItemAdd } from '../../shared/model/cart.model';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  platformId=inject(PLATFORM_ID);


  http=inject(HttpClient);

  private keyStorage="cart";


  private addedToCart$=new BehaviorSubject<Array<CartItemAdd>>([]);

  addedToCart=this.addedToCart$.asObservable();

constructor(){
  const cartItems = this.getCartFromLocalStorage();
  this.addedToCart$.next(cartItems);

}


private getCartFromLocalStorage(): Array<CartItemAdd> {
  if (isPlatformBrowser(this.platformId)) {
    const cartProducts = localStorage.getItem(this.keyStorage);

    if (cartProducts) {
      return JSON.parse(cartProducts) as CartItemAdd[];
    } else {
      return [];
    }

  } else {
    return [];
  }
}

addTocart(publicId:string,command:'add'|'remove'):void{
      if(isPlatformBrowser(this.platformId)){
        const itemTocart:CartItemAdd={publicId,quantity:1};
         
       const cartFromLocalStorage=this.getCartFromLocalStorage();

       if(cartFromLocalStorage.length!==0){
         const productsExisted=cartFromLocalStorage.find(item=>item.publicId === publicId);
         if(productsExisted){
          if(command==='add'){
             itemTocart.quantity++;
          }else if(command==='remove'){
            itemTocart.quantity--;
          }
         }else{
         cartFromLocalStorage.push(itemTocart)
       }
       }else{
         cartFromLocalStorage.push(itemTocart)
       }

      }

     
}
  
}
