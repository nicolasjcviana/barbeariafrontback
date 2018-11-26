import { UtilsService } from './../../commons/utils.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ServicoService } from './../servico.service';
import { DateLocaleService } from './../../commons/date-locale.service';
import { MenuItem } from 'primeng/api';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Servico } from './../servico';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-servico',
  templateUrl: './edit-servico.component.html',
  styleUrls: ['./edit-servico.component.css']
})
export class EditServicoComponent implements OnInit {

  private formServico : FormGroup;
  private itemsBreadcrumb : MenuItem[];
  private pt : any;
  private id : any;

  constructor(private formBuilder : FormBuilder,
              private datePt : DateLocaleService,
              private servicoService : ServicoService,
              private router : Router,
              private route : ActivatedRoute,
              private utils : UtilsService) { }

  ngOnInit() {
    this.itemsBreadcrumb = [
      {label : "Home", routerLink : "/home"},
      {label : "Servicos", routerLink : "/servico"},
      {label : "Novo serviço", routerLink : "/servico/new"}
    ];

    this.formServico = this.formBuilder.group({
      'dsServico': new FormControl('', Validators.required),
      'vlServico': new FormControl('', [Validators.required, Validators.min(0)])
    });

    this.route.params.subscribe(params => {
      this.id = params['id'];
      if (!this.id) {
        return;
      }

      this.servicoService.getServico(this.id)
                              .then(serv => {
                                this.utils.fillForm(this.formServico, serv);
                              });
    });
  }

  salvar() {
    let servico = this.formServico.value;
    servico.cdServico = this.id;
    this.servicoService.save(servico).then(result => {
      this.router.navigate(['servico']);
    });
  }

}
