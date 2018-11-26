import { UtilsService } from './../../commons/utils.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ClienteService } from './../cliente.service';
import { DateLocaleService } from './../../commons/date-locale.service';
import { MenuItem } from 'primeng/api';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Cliente } from './../cliente';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-cliente',
  templateUrl: './edit-cliente.component.html',
  styleUrls: ['./edit-cliente.component.css']
})
export class EditClienteComponent implements OnInit {

  private formCliente : FormGroup;
  private itemsBreadcrumb : MenuItem[];
  private pt : any;
  private id : any;

  constructor(private formBuilder : FormBuilder,
              private datePt : DateLocaleService,
              private clienteService : ClienteService,
              private router : Router,
              private route : ActivatedRoute,
              private utils : UtilsService) { }

  ngOnInit() {
    this.itemsBreadcrumb = [
      {label : "Home", routerLink : "/home"},
      {label : "Clientes", routerLink : "/cliente"},
      {label : "Novo cliente", routerLink : "/cliente/new"}
    ];

    this.formCliente = this.formBuilder.group({
      'nmCliente': new FormControl('', Validators.required),
      'dsEmail': new FormControl('', Validators.required),
      'nrTelefone' : new FormControl('', Validators.required),
      'dtNascimento' : new FormControl('', Validators.required),
      'dsEndereco' : new FormControl('', Validators.required),
      'dsObservacao' : new FormControl('')
    });

    this.route.params.subscribe(params => {
      this.id = params['id'];
      if (!this.id) {
        return;
      }

      this.clienteService.getCliente(this.id)
                              .then(cli => {
                                this.utils.fillForm(this.formCliente, cli);
                                this.formCliente.controls['dtNascimento'].setValue(this.utils.formatarDataTela(cli.dtNascimento));
                              });
    });
  }

  salvar() {
    let cliente = this.formCliente.value;
    cliente.cdCliente = this.id;
    cliente.dtNascimento = this.utils.formatarDataServico(cliente.dtNascimento);

    this.clienteService.save(cliente).then(result => {
      this.router.navigate(['cliente']);
    });
  }

}
