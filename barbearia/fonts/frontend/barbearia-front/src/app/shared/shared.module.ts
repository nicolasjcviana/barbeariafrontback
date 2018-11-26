import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {PasswordModule} from 'primeng/password';
import {CheckboxModule} from 'primeng/checkbox';
import {DialogModule} from 'primeng/dialog';
import {BreadcrumbModule} from 'primeng/breadcrumb';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {InputMaskModule} from 'primeng/inputmask';
import {CalendarModule} from 'primeng/calendar';
import {DropdownModule} from 'primeng/dropdown';
import {InputTextareaModule} from 'primeng/inputtextarea';

@NgModule({
  imports: [
    CommonModule
  ],
  exports : [ButtonModule, InputTextModule, FormsModule, ReactiveFormsModule, PasswordModule,
             CheckboxModule, DialogModule, BreadcrumbModule, ConfirmDialogModule, InputMaskModule, CalendarModule,
             DropdownModule, InputTextareaModule, HttpClientModule],
  declarations: []
})
export class SharedModule { }
