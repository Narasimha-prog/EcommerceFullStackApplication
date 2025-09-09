import { Component, inject, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { OrderedItems } from '../../shared/model/order.model';
import { Pagination } from '../../shared/model/request.model';
import { injectQuery } from '@tanstack/angular-query-experimental';
import { lastValueFrom } from 'rxjs';
import { OrderService } from '../../shared/service/order-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'app-admin-orders',
  imports: [CommonModule,FontAwesomeModule],
  templateUrl: './admin-orders.html',
  styleUrl: './admin-orders.scss',
})
export class AdminOrders {
  
  orderService=inject(OrderService);

  pageRequest:Pagination={
    page:0,
    size:20,
    sort:[]
  }

  platformId=inject(PLATFORM_ID);

  orderQuery=injectQuery(()=>({
    queryKey:['admin-orders',this.pageRequest],
    queryFn: ()=>lastValueFrom(this.orderService.getOrderForAdmin(this.pageRequest))
  }))


  computeItemName(items:OrderedItems[]){
       return items.map(item=>item.name).join(', ');
  }

  computeItemQuantity(items:OrderedItems[]){
       return items.reduce((acc,item)=>acc+item.quantity,0);
  }

  computeTotal(items:OrderedItems[]){
    return items.reduce((acc,item)=>acc+item.quantity*item.price,0);
  }

  checkIfPlatFormBrowser():boolean{
    return isPlatformBrowser(this.platformId);
  }
}
