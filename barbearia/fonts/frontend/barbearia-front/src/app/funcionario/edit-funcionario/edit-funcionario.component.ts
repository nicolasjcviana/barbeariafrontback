import { UtilsService } from './../../commons/utils.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FuncionarioService } from './../funcionario.service';
import { DateLocaleService } from './../../commons/date-locale.service';
import { MenuItem } from 'primeng/api';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Funcionario } from './../funcionario';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-funcionario',
  templateUrl: './edit-funcionario.component.html',
  styleUrls: ['./edit-funcionario.component.css']
})
export class EditFuncionarioComponent implements OnInit {

  private formFuncionario : FormGroup;
  private itemsBreadcrumb : MenuItem[];
  private pt : any;
  private tipoSelecionado : any;
  private tiposFuncionarios : any[];
  private id : any;

  constructor(private formBuilder : FormBuilder,
              private datePt : DateLocaleService,
              private funcionarioService : FuncionarioService,
              private router : Router,
              private route : ActivatedRoute,
              private utils : UtilsService) { }

  ngOnInit() {
    this.itemsBreadcrumb = [
      {label : "Home", routerLink : "/home"},
      {label : "Funcionários", routerLink : "/funcionario"},
      {label : "Novo funcionario", routerLink : "/funcionario/new"}
    ];

    this.formFuncionario = this.formBuilder.group({
      'nmFuncionario': new FormControl('', Validators.required),
      'dsEmail': new FormControl('', Validators.required),
      'nrTelefone' : new FormControl('', Validators.required),
      'dtNascimento' : new FormControl('', Validators.required),
      'flTipo' : new FormControl('', Validators.required),
      'dsEndereco' : new FormControl('', Validators.required),
      'dsObservacao' : new FormControl(''),
      'vlSalario' : new FormControl('', [Validators.required, Validators.min(0)]),
      'nmUsuario' : new FormControl('')
    });

    this.tiposFuncionarios = [
      {label : "Atendente", value : "A"},
      {label : "Funcionário", value : "F"},
      {label : "Gerente", value : "G"}
    ];

    this.route.params.subscribe(params => {
      this.id = params['id'];
      if (!this.id) {
        return;
      }

      this.funcionarioService.getFuncionario(this.id)
                              .then(func => {
                                this.utils.fillForm(this.formFuncionario, func);
                                this.formFuncionario.controls['dtNascimento'].setValue(this.utils.formatarDataTela(func.dtNascimento));
                                this.formFuncionario.controls['flTipo'].setValue(this.tiposFuncionarios.find(o => o.value === func.flTipo));
                              });
    });
  }

  salvar() {
    let funcionario = this.formFuncionario.value;
    funcionario.cdFuncionario = this.id;
    funcionario.flTipo = funcionario.flTipo.value;
    funcionario.dtNascimento = this.utils.formatarDataServico(funcionario.dtNascimento);

    this.funcionarioService.save(funcionario).then(result => {
      this.router.navigate(['funcionario']);
    });
  }

}
