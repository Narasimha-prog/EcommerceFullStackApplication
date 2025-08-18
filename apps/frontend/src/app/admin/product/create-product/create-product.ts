import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormControl } from '@angular/forms';
import { AdminProductService } from '../../admin-product';
import { Toast } from '../../../shared/model/toast/toast';
import { Router } from '@angular/router';
import { ProductPicture } from '../../model/product.model';

@Component({
  selector: 'app-create-product',
  imports: [CommonModule],
  templateUrl: './create-product.html',
  styleUrl: './create-product.scss',
})
export class CreateProductComponent {
  formBuilder=inject(FormBuilder);
  productService=inject(AdminProductService);
  toastService=inject(Toast);
  router=inject(Router);

  public productPictures=new Array<ProductPicture>();

name=new FormControl<string>('',{nonNullable: true});
  
}
