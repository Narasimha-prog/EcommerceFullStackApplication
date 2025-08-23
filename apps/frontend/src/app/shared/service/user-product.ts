import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { createPaginationOption, Page, Pagination } from '../model/request.model';
import { Product } from '../../admin/model/product.model';
import { Observable } from 'rxjs';
import { environment } from '../../../environment/environment.devlopment';


@Injectable({
  providedIn: 'root'
})
export class UserProductService {

  http=inject(HttpClient);

  public findAllFeaturedProducts(pageRequest:Pagination):Observable<Page<Product>>{
    const params=createPaginationOption(pageRequest);
    

    return this.http.get<Page<Product>>(`${environment.apiUrl}/products-shop/featured`,{params});

  }

}
