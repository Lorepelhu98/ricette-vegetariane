import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransformPipe } from './transform.pipe';



@NgModule({
  declarations: [
    TransformPipe
  ],
  exports: [TransformPipe],
  imports: [
    CommonModule
  ]
})
export class SharedModule { }
