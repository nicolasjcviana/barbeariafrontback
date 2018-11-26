import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListAtendimentoComponent } from './list-atendimento/list-atendimento.component';
import {PanelModule} from 'primeng/panel';
import {TableModule} from 'primeng/table';
import { EditAtendimentoComponent } from './edit-atendimento/edit-atendimento.component';
import { PainelComponent } from './painel/painel.component';
import {CardModule} from 'primeng/card';
import {AutoCompleteModule} from 'primeng/autocomplete';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    PanelModule,
    TableModule,
    CardModule,
    AutoCompleteModule
  ],
  declarations: [ListAtendimentoComponent, EditAtendimentoComponent, PainelComponent]
})
export class AtendimentoModule { }
