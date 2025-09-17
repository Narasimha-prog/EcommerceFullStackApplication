import { Component, effect, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserProductService } from '../../shared/service/user-product';
import { firstValueFrom } from 'rxjs';
import { injectQuery } from '@tanstack/angular-query-experimental';
import { Pagination } from '../../shared/model/request.model';
import { Router, RouterLink } from '@angular/router';
import { injectQueryParams } from 'ngxtension/inject-query-params';
import { Toast } from '../../shared/model/toast/toast';
import { ProductFilter } from '../../admin/model/product.model';
import { ProductsFilterComponent } from '../products/filter-product/filter-product';
import { ProductCard } from '../../hero/product-card';

@Component({
  selector: 'app-category-filter',
  imports: [CommonModule,RouterLink,ProductsFilterComponent,ProductCard],
  templateUrl: './category-filter.html',
  styleUrl: './category-filter.scss',
})
export class CategoryFilter{ 
  
  // 🔹 Query params
  category = injectQueryParams('category');
  size = injectQueryParams('size');
  sort = injectQueryParams('sort');  
  productService =inject(UserProductService);


  // 🔹 Services

  router = inject(Router);
  toastService = inject(Toast);

  // 🔹 Pagination
  pageRequest: Pagination = {
    page: 0,
    size: 20,
    sort: ['createdDate,desc'],
  };

  // 🔹 Filters
  filterProducts: ProductFilter = {
    category: this.category(),
    size: this.size() ? this.size()! : '',
    sort: [this.sort() ? this.sort()! : ''],
  };

  lastCategory = '';

  constructor() {
    effect(() => this.handleFilteredProductsQueryError());
    effect(() => this.handleParametersChange());
  }

  // 🔹 Queries
  categoryQuery = injectQuery(() => ({
    queryKey: ['categories'],
    queryFn: () => firstValueFrom(this.productService.findAllCategories()),
  }));

  featuredProductQuery = injectQuery(() => ({
    queryKey: ['featured-products', this.pageRequest],
    queryFn: () =>
      firstValueFrom(this.productService.findAllFeaturedProducts(this.pageRequest)),
  }));

  filteredProductsQuery = injectQuery(() => ({
    queryKey: ['products', this.filterProducts],
    queryFn: () =>
      firstValueFrom(
        this.productService.filter(this.pageRequest, this.filterProducts)
      ),
       enabled: !!this.category(),
  }));

  // 🔹 Filter change handler
  onFilterChange(filterProducts: ProductFilter) {
    filterProducts.category = this.category();
    this.filterProducts = filterProducts;
    this.pageRequest.sort = filterProducts.sort;

    this.router.navigate(['/shop/collection/products'], {
      queryParams: { ...filterProducts },
    });

    this.filteredProductsQuery.refetch();
  }

  // 🔹 Error handler
  private handleFilteredProductsQueryError() {
    if (this.filteredProductsQuery.isError()) {
      this.toastService.show(
        'Error! Failed to load products, please try again',
        'ERROR'
      );
    }
  }

  // 🔹 Watch query param changes
  private handleParametersChange() {
    if (this.category()) {
      if (this.lastCategory !== this.category() && this.lastCategory !== '') {
        this.filterProducts = {
          category: this.category(),
          size: this.size() ? this.size()! : '',
          sort: [this.sort() ? this.sort()! : ''],
        };
        this.filteredProductsQuery.refetch();
      }
    }
    this.lastCategory = this.category()!;
  }
}