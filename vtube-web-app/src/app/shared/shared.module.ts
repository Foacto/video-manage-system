import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LayoutModule } from '../modules/common/layout/layout.module';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { DropdownModule } from 'primeng/dropdown';
import { BrowserModule } from '@angular/platform-browser';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    // layout
    LayoutModule,
    // PrimeNG
    BreadcrumbModule,
    DropdownModule,
  ],
  providers: [],
  declarations: [],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    // layout
    LayoutModule,
    // PrimeNG
    BreadcrumbModule,
    DropdownModule,
  ],
})
export class SharedModule {}
