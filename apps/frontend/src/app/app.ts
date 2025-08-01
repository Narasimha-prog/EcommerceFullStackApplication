import { Component, inject, OnInit, PLATFORM_ID } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FaConfig, FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { fontAwesomeIcons } from './shared/font-awsome-icons';
import { Navbar } from './layout/navbar/navbar';
import { Footer } from "./layout/footer/footer";
import { Oauth2Service } from './auth/oauth2';
import { isPlatformBrowser } from '@angular/common';


@Component({
  standalone: true,
  imports: [RouterModule, FontAwesomeModule, Navbar, Footer],
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App implements OnInit {
   private faIconLibrary = inject(FaIconLibrary);
  private faConfig = inject(FaConfig);

private oauth2Service = inject(Oauth2Service);

platformId = inject(PLATFORM_ID);

constructor(){
if(isPlatformBrowser(this.platformId)){
  this.oauth2Service.initAuthentication();
}

this.oauth2Service.connectedUserQuery= this.oauth2Service.fetch();

}
  ngOnInit() {
    this.initFontAwesome();
  }
   private initFontAwesome() {
    this.faConfig.defaultPrefix = 'far';
    this.faIconLibrary.addIcons(...fontAwesomeIcons);
  }
 
}
