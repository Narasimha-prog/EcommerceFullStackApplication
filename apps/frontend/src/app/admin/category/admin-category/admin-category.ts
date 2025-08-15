import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-admin-category',
  imports: [CommonModule,RouterLink],
  templateUrl: './admin-category.html',
  styleUrl: './admin-category.scss',
})
export class AdminCategory {}
