import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListServicoComponent } from './list-servico/list-servico.component';
import {PanelModule} from 'primeng/panel';
import {TableModule} from 'primeng/table';
import { EditServicoComponent } from './edit-servico/edit-servico.component';
import { CurrencyMaskModule } from 'ng2-currency-mask';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    PanelModule,
    TableModule,
    CurrencyMaskModule
  ],
  declarations: [ListServicoComponent, EditServicoComponent]
})
export class ServicoModule { }
