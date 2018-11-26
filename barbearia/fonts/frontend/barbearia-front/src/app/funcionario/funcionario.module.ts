import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListFuncionarioComponent } from './list-funcionario/list-funcionario.component';
import {PanelModule} from 'primeng/panel';
import {TableModule} from 'primeng/table';
import { EditFuncionarioComponent } from './edit-funcionario/edit-funcionario.component';
import { CurrencyMaskModule } from 'ng2-currency-mask';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    PanelModule,
    TableModule,
    CurrencyMaskModule
  ],
  declarations: [ListFuncionarioComponent, EditFuncionarioComponent]
})
export class FuncionarioModule { }
