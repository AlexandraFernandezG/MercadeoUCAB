import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatSortModule } from '@angular/material/sort';

const materialModules = [
  MatIconModule
];

@NgModule({
  imports: [
    CommonModule,
    ...materialModules,
    MatSortModule,
  ],
  exports: [
    ...materialModules,
    MatSortModule,
  ],
})

export class AngularMaterialModule { }
