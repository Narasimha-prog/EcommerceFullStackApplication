import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductDetails } from '../product-details/product-details';
import { injectParams } from 'ngxtension/inject-params';
import { FilterProduct } from '../filter-product/filter-product';
import { ProductFilter, RequestSort } from '../../shared/model/request.model';

@Component({
  selector: 'app-products',
  imports: [CommonModule,FilterProduct],
  templateUrl: './products.html',
  styleUrl: './products.scss',
})
export class ProductsComponent {
  
onFilterChange($event: ProductFilter) {
throw new Error('Method not implemented.');
}


  category=injectParams('category');
  size=injectParams('size');
  sort=injectParams('sort');
  
    parseSort(sortParam: string | null): RequestSort {
    if (!sortParam) {
      return { property: 'createdDate', direction: 'DESC' }; // default
    }
    const [property, direction] = sortParam.split(',') as [any, 'ASC' | 'DESC'];
    return {
      property,
      direction: direction ?? 'DESC',
    };
  }

 
}
