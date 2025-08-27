import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome'
import { Oauth2Service } from '../../auth/oauth2';
import { RouterLink } from '@angular/router';
import { UserProductService } from '../../shared/service/user-product';
import { injectQuery } from '@tanstack/angular-query-experimental';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, FontAwesomeModule,RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
})
export class Navbar {
  

closeMenu(menu: HTMLDetailsElement) {
menu.removeAttribute('open');
}
  oauth2Service = inject(Oauth2Service);
  
  productService=inject(UserProductService);


  categoryQuery=injectQuery(()=>({
    queryKey:['categories'],
    queryFn:()=> firstValueFrom(this.productService.findAllCategories())
  }))


  connectedUserQuery = this.oauth2Service.connectedUserQuery;


  logIn():void { 
    console.log('Logging in...');  
    this.closeDropDownMenu();
     this.oauth2Service.logIn();
  }

  logOut():void {
    this.closeDropDownMenu();
    this.oauth2Service.logOut();
  }

  isConnected(): boolean {
    return this.connectedUserQuery?.status()==='success'&&  this.connectedUserQuery.data()?.email !== this.oauth2Service.notConnected;
  }

  closeDropDownMenu() {
    const bodyElement=document.activeElement as HTMLElement;

    if (bodyElement) {
      bodyElement.blur();
    }
  }
}
