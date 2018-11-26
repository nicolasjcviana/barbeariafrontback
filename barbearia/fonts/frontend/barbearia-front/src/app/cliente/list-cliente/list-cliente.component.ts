import { ClienteService } from './../cliente.service';
import { Cliente } from './../cliente';
import { Component, OnInit } from '@angular/core';
import { MenuItem, ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-list-cliente',
  templateUrl: './list-cliente.component.html',
  styleUrls: ['./list-cliente.component.css'],
  providers : [ConfirmationService]
})
export class ListClienteComponent implements OnInit {

  private itemsBreadcrumb: MenuItem[];
  private clientes : Cliente[];
  private columns : any[];

  constructor(private confirmationService : ConfirmationService,
              private service : ClienteService) { }

  ngOnInit() {
    this.itemsBreadcrumb = [
      {label : "Home", routerLink : "/home"},
      {label : "Clientes", routerLink : "/cliente"}
    ];
    this.columns = [
      {field: "nmCliente", header: "Nome"},
      {field: "dsEmail", header: "E-mail"},
      {field: "nrTelefone", header: "Telefone"},
      {field: "dtNascimento", header: "Data de nascimento"},
      {field: "dsEndereco", header: "Endereço"}
    ]
    this.listar();
  }

  listar() {
    this.service.getAll().then(data => {
      this.clientes = data;
    })
  }

  excluir(cliente) {
    this.confirmationService.confirm({
      message : `Deseja realmente excluir o cliente ${cliente.nmCliente}?`,
      accept : () => {
        this.service.delete(cliente.cdCliente)
                    .then(o => {
                      this.listar();
                    });
      },
      acceptLabel : "Sim",
      rejectLabel : "Não"
    })
  }

}
