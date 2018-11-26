import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListClienteComponent } from './list-cliente/list-cliente.component';
import {PanelModule} from 'primeng/panel';
import {TableModule} from 'primeng/table';
import { EditClienteComponent } from './edit-cliente/edit-cliente.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    PanelModule,
    TableModule
  ],
  declarations: [ListClienteComponent, EditClienteComponent]
})
export class ClienteModule { }
