import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import {GrowlModule} from 'primeng/growl';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    GrowlModule
  ],
  declarations: [LoginComponent],
  entryComponents : [LoginComponent]
})
export class LoginModule { }
