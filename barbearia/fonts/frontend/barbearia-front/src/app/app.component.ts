import { LogadoEventEmitterService } from './login/logadoeventemitter.service';
import { AuthService } from './auth/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component } from '@angular/core';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  items: MenuItem[];
  itemsMenu: MenuItem[];
  display : Boolean = false;
  isLogado : Boolean = false;

  constructor (
          private router : Router,
          private route : ActivatedRoute,
          private auth : AuthService,
          private logadoEventEmitter : LogadoEventEmitterService)
  {

  }

  ngOnInit() {
    this.items = [
      {
        label: 'Barbearia',
        icon: "fas fa-bars",
        command : (event) => {
          this.toggleSidebar(event);
        }
      }];
      this.itemsMenu = [{
        label: 'Clientes',
        routerLink : 'cliente',
        command : (event) => {
          this.toggleSidebar(event);
        }
      },{
        label: 'Funcionários',
         routerLink : 'funcionario',
        command : (event) => {
          this.toggleSidebar(event);
        }
      },{
        label: 'Serviços',
        routerLink : 'servico',
        command : (event) => {
          this.toggleSidebar(event);
        }
      },{
        label: 'Atendimentos',
        routerLink : 'atendimento',
        command : (event) => {
          this.toggleSidebar(event);
        }
      }]

      this.isLogado = !this.auth.isAccessTokenInvalido();

      this.logadoEventEmitter.emitter.subscribe(() => {
        this.isLogado = true;
      })
    }

    toggleSidebar(e : Event) {
      this.display = !this.display;
    }

    logout() {
      this.auth.logout();
      this.router.navigate(['login']);
      this.isLogado = false;
    }
  }
