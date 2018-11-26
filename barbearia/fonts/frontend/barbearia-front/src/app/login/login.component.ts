import { LogadoEventEmitterService } from './logadoeventemitter.service';
import { Atendimento } from './../atendimento/atendimento';
import { UtilsService } from './../commons/utils.service';
import { Router } from '@angular/router';
import { AuthService } from './../auth/auth.service';
import {Component,OnInit} from '@angular/core';
import {Validators,FormControl,FormGroup,FormBuilder} from '@angular/forms';
import {Message} from 'primeng/api';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  mostrarEsqueciSenha : Boolean = false;

  formLogin : FormGroup;

  msgs : Message[] = [];

  constructor(private formBuilder : FormBuilder,
              private auth : AuthService,
              private router : Router,
              private utils : UtilsService,
              private logadoEventEmitterService : LogadoEventEmitterService) { }

  ngOnInit() {
    this.formLogin = this.formBuilder.group({
            'usuario': new FormControl('', Validators.required),
            'senha': new FormControl('', Validators.required),
            'conectado': new FormControl('')
        });
  }

  toggleEsqueciSenha() {
    this.mostrarEsqueciSenha = true;
  }

  realizarLogin(formLogin) {
    console.log(formLogin);
    this.auth.login(formLogin.usuario, formLogin.senha)
    .then(o => {
      this.logadoEventEmitterService.emitter.emit();
      this.router.navigate(['atendimento']);
    })
    .catch(o => {
       this.msgs.push({severity:'error', summary:'', detail:o});
    })
  }

}
