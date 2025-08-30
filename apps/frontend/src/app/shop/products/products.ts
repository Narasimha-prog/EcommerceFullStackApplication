import { Component, computed, effect, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { injectParams } from 'ngxtension/inject-params';
import { FilterProduct } from './filter-product/filter-product';
import { Pagination, ProductFilter, RequestSort } from '../../shared/model/request.model';
import { UserProductService } from '../../shared/service/user-product';
import { Router } from '@angular/router';
import { Toast } from '../../shared/model/toast/toast';
import { injectQuery } from '@tanstack/angular-query-experimental';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-products',
  imports: [CommonModule, FilterProduct],
  templateUrl: './products.html',
  styleUrl: './products.scss',
})
export class ProductsComponent {
  productService = inject(UserProductService);
  router = inject(Router);
  toastService = inject(Toast);

  pageRequest: Pagination = {
    page: 0,
    size: 20,
    sort: [{ property: 'createdDate', direction: 'DESC' }]
  };

  // URL params
  category = injectParams('category');
  size = injectParams('size');
  sort = injectParams('sort');

  // Build ProductFilter reactively from URL params
  FilterProduct = computed(() =>
    this.queryParamsToProductFilter(this.category(), this.size(), this.sort())
  );

  // Query based on filter
  filteredProductQuery = injectQuery(() => ({
    queryKey: ['products', this.FilterProduct()],
    queryFn: () =>
      firstValueFrom(this.productService.filter(this.pageRequest, this.FilterProduct()))
  }));

  // --- Handlers ---
  onFilterChange(filterProducts: ProductFilter) {
    // Safe navigation with serialization
    this.router.navigate(['/products'], {
      queryParams: this.productFilterToQueryParams(filterProducts),
    });
    this.filteredProductQuery.refetch();
  }

  private handleFilteredProductsqueryError() {
    if (this.filteredProductQuery.isError()) {
      this.toastService.show(
        'Error! While loading products...Please Try again ',
        'ERROR'
      );
    }
  }

  constructor() {
    effect(() => this.handleFilteredProductsqueryError());
  }

  // --- Helpers ---
  public parseSort(sortParam: string | null): RequestSort {
    if (!sortParam) {
      return { property: 'createdDate', direction: 'DESC' }; // default
    }
    const [property, direction] = sortParam.split(',') as [
      any,
      'ASC' | 'DESC'
    ];
    return {
      property,
      direction: direction ?? 'DESC',
    };
  }

  queryParamsToProductFilter(
    category: string | null,
    size: string | null,
    sort: string | null
  ): ProductFilter {
    return {
      category: category ?? undefined,
      size: size ? size.split(',') : undefined,
      sort: sort
        ? sort.split(';').map(s => this.parseSort(s))
        : [{ property: 'createdDate', direction: 'DESC' }]
    };
  }

  productFilterToQueryParams(filter: ProductFilter) {
    return {
      category: filter.category,
      size: Array.isArray(filter.size) ? filter.size.join(',') : filter.size,
      sort: filter.sort
        ? filter.sort.map(s => `${s.property},${s.direction}`).join(';')
        : undefined
    };
  }
}
