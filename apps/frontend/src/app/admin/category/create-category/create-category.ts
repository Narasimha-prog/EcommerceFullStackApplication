import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Toast } from '../../../shared/model/toast/toast';
import { Router } from '@angular/router';
import { CreateCategoryFormContent } from '../../model/product.model';

@Component({
  selector: 'app-create-category',
  imports: [CommonModule],
  templateUrl: './create-category.html',
  styleUrl: './create-category.scss',
})
export class CreateCategory {

  formBuilder = inject(FormBuilder);
  productService = inject(AdminProductService);
  toastService = inject(Toast);
  router = inject(Router);

  name=new FormControl<string>('',{nonNullable: true,validators:[Validators.required]});

  public createCategoryForm = this.formBuilder.nonNullable.group<CreateCategoryFormContent>({
    name:this.name,
  })
  
  loading=false;

  
}
