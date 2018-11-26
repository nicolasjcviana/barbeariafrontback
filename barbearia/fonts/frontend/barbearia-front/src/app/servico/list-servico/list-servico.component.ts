import { ServicoService } from './../servico.service';
import { Servico } from './../servico';
import { Component, OnInit } from '@angular/core';
import { MenuItem, ConfirmationService } from 'primeng/api';
import {LOCALE_ID} from '@angular/core';

@Component({
  selector: 'app-list-servico',
  templateUrl: './list-servico.component.html',
  styleUrls: ['./list-servico.component.css'],
  providers : [ConfirmationService, {provide: LOCALE_ID, useValue: "pt-BR"}]
})
export class ListServicoComponent implements OnInit {

  private itemsBreadcrumb: MenuItem[];
  private servicos : Servico[];
  private columns : any[];

  constructor(private confirmationService : ConfirmationService,
              private service : ServicoService) { }

  ngOnInit() {
    this.itemsBreadcrumb = [
      {label : "Home", routerLink : "/home"},
      {label : "Servicos", routerLink : "/servico"}
    ];
    this.columns = [
      {field: "dsServico", header: "Descrição"},
      {field: "vlServico", header: "Valor"}
    ]
    this.listar();
  }

  listar() {
    this.service.getAll().then(data => {
      this.servicos = data;
    })
  }

  excluir(servico) {
    this.confirmationService.confirm({
      message : `Deseja realmente excluir o serviço ${servico.dsServico}?`,
      accept : () => {
        this.service.delete(servico.cdServico)
                    .then(o => {
                      this.listar();
                    });
      },
      acceptLabel : "Sim",
      rejectLabel : "Não"
    })
  }

}
