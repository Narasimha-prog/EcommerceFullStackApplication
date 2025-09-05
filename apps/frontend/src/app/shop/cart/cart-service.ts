import { HttpClient } from '@angular/common/http';
import { inject, Injectable, PLATFORM_ID } from '@angular/core';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { Cart, CartItemAdd } from '../../shared/model/cart.model';
import { isPlatformBrowser } from '@angular/common';
import { environment } from '../../../environment/environment.devlopment';



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

addToCart(publicId:string,command:'add'|'remove'):void{
      if(isPlatformBrowser(this.platformId)){
        const itemTocart:CartItemAdd={publicId,quantity:1};
         
       const cartFromLocalStorage=this.getCartFromLocalStorage();

       if(cartFromLocalStorage.length!==0){
         const productsExisted=cartFromLocalStorage.find(item=>item.publicId === publicId);
         if(productsExisted){
          if(command==='add'){
             productsExisted.quantity++;
          }else if(command==='remove'){
            productsExisted.quantity--;
          }
         }else{
         cartFromLocalStorage.push(itemTocart)
       }
       }else{
         cartFromLocalStorage.push(itemTocart)
       }

     localStorage.setItem(this.keyStorage,JSON.stringify(cartFromLocalStorage));
     this.addedToCart$.next(cartFromLocalStorage);
      

      }

}
 
removeFromCart(publicId:string):void{

if(isPlatformBrowser(this.platformId)){
   const cartFromLocalStorage=this.getCartFromLocalStorage();
   const productExisit=cartFromLocalStorage.find(item=>item.publicId===publicId);

   if(productExisit){
    cartFromLocalStorage.splice(cartFromLocalStorage.indexOf(productExisit),1);
    localStorage.setItem(this.keyStorage,JSON.stringify(cartFromLocalStorage));
     this.addedToCart$.next(cartFromLocalStorage);
      
   }
}
}

getCartDetails():Observable<Cart>{
  const cartFromLocalStorage=this.getCartFromLocalStorage();
  const publicIdsForURL = cartFromLocalStorage.reduce((acc: string, item) => `${acc}${acc.length > 0 ? ',' : ''}${item.publicId}`, '');
   return this.http.get<Cart>(`${environment.apiUrl}/orders/get-cart-details`,{params:{productIds:publicIdsForURL}})
   .pipe(
    map(cart=>this.mapToQuantity(cart,cartFromLocalStorage))
   );


}
  private mapToQuantity(cart: Cart, cartFromLocalStorage: CartItemAdd[]):Cart {
     for(const cartItem of cartFromLocalStorage){
      const foundProduct=cart.products.find(item=>item.publicId===cartItem.publicId);

      if(foundProduct){
        foundProduct.quantity=cartItem.quantity;
      }
     }
     return cart;
  }

}
