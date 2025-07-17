import { Component, inject, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FaConfig, FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { fontAwesomeIcons } from './shared/font-awsome-icons';
import { Navbar } from './layout/navbar/navbar';


@Component({
  standalone: true,
  imports: [RouterModule, FontAwesomeModule, Navbar],
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App implements OnInit {
   private faIconLibrary = inject(FaIconLibrary);
  private faConfig = inject(FaConfig);



  ngOnInit() {
    this.initFontAwesome();
  }
   private initFontAwesome() {
    this.faConfig.defaultPrefix = 'far';
    this.faIconLibrary.addIcons(...fontAwesomeIcons);
  }
 
}
