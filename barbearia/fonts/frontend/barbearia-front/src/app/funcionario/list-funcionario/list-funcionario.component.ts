import { FuncionarioService } from './../funcionario.service';
import { Funcionario } from './../funcionario';
import { Component, OnInit } from '@angular/core';
import { MenuItem, ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-list-funcionario',
  templateUrl: './list-funcionario.component.html',
  styleUrls: ['./list-funcionario.component.css'],
  providers : [ConfirmationService]
})
export class ListFuncionarioComponent implements OnInit {

  private itemsBreadcrumb: MenuItem[];
  private funcionarios : Funcionario[];
  private columns : any[];

  constructor(private confirmationService : ConfirmationService,
              private service : FuncionarioService) { }

  ngOnInit() {
    this.itemsBreadcrumb = [
      {label : "Home", routerLink : "/home"},
      {label : "Funcionários", routerLink : "/funcionario"}
    ];
    this.columns = [
      {field: "nmFuncionario", header: "Nome"},
      {field: "dsEmail", header: "E-mail"},
      {field: "nrTelefone", header: "Telefone"},
      {field: "dtNascimento", header: "Data de nascimento"},
      {field: "dsEndereco", header: "Endereço"}
    ]
    this.listar();
  }

  listar() {
    this.service.getAll().then(data => {
      this.funcionarios = data;
    })
  }

  excluir(func) {
    this.confirmationService.confirm({
      message : `Deseja realmente excluir o funcionário ${func.nmFuncionario}?`,
      accept : () => {
        this.service.delete(func.cdFuncionario)
                    .then(o => {
                      this.listar();
                    });
      },
      acceptLabel : "Sim",
      rejectLabel : "Não"
    })
  }

}
