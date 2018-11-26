import { ClienteService } from './../../cliente/cliente.service';
import { FuncionarioService } from './../../funcionario/funcionario.service';
import { ServicoService } from './../../servico/servico.service';
import { UtilsService } from './../../commons/utils.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AtendimentoService } from './../atendimento.service';
import { DateLocaleService } from './../../commons/date-locale.service';
import { MenuItem } from 'primeng/api';
import { FormBuilder, FormGroup, FormControl, Validators, AbstractControl, ValidatorFn } from '@angular/forms';
import { Atendimento } from './../atendimento';
import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'app-edit-atendimento',
  templateUrl: './edit-atendimento.component.html',
  styleUrls: ['./edit-atendimento.component.css']
})
export class EditAtendimentoComponent implements OnInit {

  private formAtendimento : FormGroup;
  private itemsBreadcrumb : MenuItem[];
  private pt : any;

  private funcionarios : any[];
  private clientes : any[];
  private funcionarioSelecionado : any;
  private clienteSelecionado : any;

  private servicos : any;
  private servicosAdicionar : any[] = [];
  private servicosAdicionados : any[] = [];

  private totalAtendimento : number = 0;

  constructor(private formBuilder : FormBuilder,
              private datePt : DateLocaleService,
              private atendimentoService : AtendimentoService,
              private servicoService : ServicoService,
              private funcionarioService : FuncionarioService,
              private clienteService : ClienteService,
              private router : Router,
              private route : ActivatedRoute,
              private utils : UtilsService) { }

  ngOnInit() {
    this.itemsBreadcrumb = [
      {label : "Home", routerLink : "/home"},
      {label : "Atendimentos", routerLink : "/atendimento"},
      {label : "Novo atendimento", routerLink : "/atendimento/new"}
    ];

    this.formAtendimento = this.formBuilder.group({
      'funcionarioSelecionado': new FormControl('', Validators.required),
      'clienteSelecionado': new FormControl('', Validators.required)
    });


    Promise.all([this.carregarClientes(), this.carregarFuncionarios()]);
  }



  carregarClientes() {
    return this.clienteService.getAll().then(data => {
      this.clientes = data;
    });
  }

  carregarFuncionarios() {
    return this.funcionarioService.getAll().then(data => {
      this.funcionarios = data;
    });
  }

  confirmarAtendimento() {
    let atendimento = this.formAtendimento.value;
    atendimento.servicos = this.servicosAdicionados;
    atendimento.totalAtendimento = this.totalAtendimento;
    this.atendimentoService.save(atendimento).then(result => {
      this.router.navigate(['atendimento']);
    });
  }

  buscarServicos(event) {
    this.servicoService.listarServicosPorNome(event.query).then(data => {
      this.servicos = data;
    });
  }

  adicionarServicosSelecionados() {
    this.servicosAdicionar.forEach(o => {
      this.servicosAdicionados.push(o);
      this.totalAtendimento += o.vlServico;
    });
    this.servicosAdicionar = [];
  }

  removerServico(servico) {
    let index = this.servicosAdicionados.findIndex(o => o.cdServico === servico.cdServico);
    this.servicosAdicionados.splice(index, 1);
    this.totalAtendimento -= servico.vlServico;
  }

  isDisabled() {
    return !this.formAtendimento.valid || this.servicosAdicionados.length == 0;
  }

}
