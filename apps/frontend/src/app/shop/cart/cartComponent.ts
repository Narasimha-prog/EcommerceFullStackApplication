import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartService } from './cart-service';
import { Oauth2Service } from '../../auth/oauth2';
import { Toast } from '../../shared/model/toast/toast';

@Component({
  selector: 'app-cart-component',
  imports: [CommonModule],
  templateUrl: './cartComponent.html',
  styleUrl: './cartComponent.scss',
})
export class CartComponent {
  cartService=inject(CartService);

  oauth2Service=inject(Oauth2Service);

  toastService=inject(Toast);

  

}
