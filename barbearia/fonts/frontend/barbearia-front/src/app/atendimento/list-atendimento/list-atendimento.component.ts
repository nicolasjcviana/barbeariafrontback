import { AtendimentoService } from './../atendimento.service';
import { Atendimento } from './../atendimento';
import { Component, OnInit } from '@angular/core';
import { MenuItem, ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-list-atendimento',
  templateUrl: './list-atendimento.component.html',
  styleUrls: ['./list-atendimento.component.css'],
  providers : [ConfirmationService]
})
export class ListAtendimentoComponent implements OnInit {

  private itemsBreadcrumb: MenuItem[];
  private atendimentos : any[];
  private columns : any[];
  private valores : any = {};

  constructor(private confirmationService : ConfirmationService,
              private service : AtendimentoService) { }
  
  getTotalReceitas() {
    this.service.getTotalReceitas().then(data => {
      this.valores.receitas = data;
    });
  }

  getTotalDespesas() {
    this.service.getTotalDespesas().then(data => {
      this.valores.despesas = data;
    });
  }

  getLucroTotal() {
    this.service.getLucroTotal().then(data => {
      this.valores.saldo = data;
    });
  }
  
  ngOnInit() {
    Promise.all([this.service.getTotalReceitas(), this.service.getTotalDespesas(), this.service.getLucroTotal()]);
    this.valores = {
      despesas : this.getTotalDespesas() || 0,
      receitas : this.getTotalReceitas() || 0,
      saldo : this.getLucroTotal() || 0
    }
    this.itemsBreadcrumb = [
      {label : "Home", routerLink : "/home"},
      {label : "Atendimentos", routerLink : "/atendimento"}
    ];
    this.columns = [
      {field: "data", header: "Data do atendimento"},
      {field: "valor", header: "Valor atendimento "},
      {field: "funionario.nome", header: "Funcionário"},
      {field: "itensAtendimento", header: "Serviços"}
    ]
    this.listar();
  }

  listar() {
    this.service.getAll().then(data => {
      this.atendimentos = data;
    })
  }

  getDescricaoItensAtendimento(itensAtendimento : any[]) {
    if (itensAtendimento) {
      return itensAtendimento.map(o => o.cdServico.dsServico).join(', ');
    }
    return '';
  }

}
