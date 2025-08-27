import { Component, inject, input, output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductFilter, RequestSort } from '../../shared/model/request.model';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-filter-product',
  imports: [CommonModule],
  templateUrl: './filter-product.html',
  styleUrl: './filter-product.scss',
})
export class FilterProduct {
  sort=input<RequestSort>(
    {
      property:'createdDate',
      direction:'DESC'
    }
  );

  size=input<string>();

  productFilter=output<ProductFilter>();


  formBuilder=inject(FormBuilder);

  

}
