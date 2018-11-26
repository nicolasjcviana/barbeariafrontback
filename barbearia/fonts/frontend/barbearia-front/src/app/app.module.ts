import { LogadoEventEmitterService } from './login/logadoeventemitter.service';
import { ErrorInterceptor } from './shared/errorhandler.service';
import { JwtInterceptor } from './shared/jwtinterceptor.service';
import { AtendimentoModule } from './atendimento/atendimento.module';
import { EditAtendimentoComponent } from './atendimento/edit-atendimento/edit-atendimento.component';
import { ListAtendimentoComponent } from './atendimento/list-atendimento/list-atendimento.component';
import { EditServicoComponent } from './servico/edit-servico/edit-servico.component';
import { ListServicoComponent } from './servico/list-servico/list-servico.component';
import { ServicoModule } from './servico/servico.module';
import { ClienteModule } from './cliente/cliente.module';
import { EditClienteComponent } from './cliente/edit-cliente/edit-cliente.component';
import { ListClienteComponent } from './cliente/list-cliente/list-cliente.component';
import { EditFuncionarioComponent } from './funcionario/edit-funcionario/edit-funcionario.component';
import { FuncionarioModule } from './funcionario/funcionario.module';
import { ListFuncionarioComponent } from './funcionario/list-funcionario/list-funcionario.component';
import { JwtModule, JwtHelperService, JWT_OPTIONS  } from '@auth0/angular-jwt';
import { HttpModule } from '@angular/http';
import { AuthGuardService } from './auth/auth-guard.service';
import { LoginModule } from './login/login.module';
import { LoginComponent } from './login/login.component';
import { SharedModule } from './shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/pt';
registerLocaleData(localePt);

import {MenubarModule} from 'primeng/menubar';
import {TieredMenuModule} from 'primeng/tieredmenu';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import {SidebarModule} from 'primeng/sidebar';
import {PanelMenuModule} from 'primeng/panelmenu';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';


const appRoutes: Routes = [
  {path: 'login', component : LoginComponent},
  {path: 'funcionario', component : ListFuncionarioComponent, canActivate : [AuthGuardService]},
  {path: 'funcionario/new', component : EditFuncionarioComponent, canActivate : [AuthGuardService]},
  {path: 'funcionario/:id', component : EditFuncionarioComponent, canActivate : [AuthGuardService]},
  {path: 'cliente', component : ListClienteComponent, canActivate : [AuthGuardService]},
  {path: 'cliente/new', component : EditClienteComponent, canActivate : [AuthGuardService]},
  {path: 'cliente/:id', component : EditClienteComponent, canActivate : [AuthGuardService]},
  {path: 'servico', component : ListServicoComponent, canActivate : [AuthGuardService]},
  {path: 'servico/new', component : EditServicoComponent, canActivate : [AuthGuardService]},
  {path: 'servico/:id', component : EditServicoComponent, canActivate : [AuthGuardService]},
  {path: 'atendimento', component : ListAtendimentoComponent, canActivate : [AuthGuardService]},
  {path: 'atendimento/new', component : EditAtendimentoComponent, canActivate : [AuthGuardService]},
  {path: 'home', component: AppComponent, canActivate : [AuthGuardService]},
  {path: '', redirectTo : 'home', pathMatch : 'full'}

]

export function tokenGetter() {
  return localStorage.getItem('token');
}



@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    SharedModule,
    MenubarModule,
    SidebarModule,
    PanelMenuModule,
    LoginModule,
    FuncionarioModule,
    ClienteModule,
    ServicoModule,
    AtendimentoModule,
    BrowserAnimationsModule,
    HttpModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        whitelistedDomains: ['localhost:4200/login'],
        blacklistedRoutes: ['localhost:4200'],
      }
    }),
    RouterModule.forRoot(
      appRoutes
    )
  ],
  providers: [AuthGuardService, JwtHelperService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    LogadoEventEmitterService,
    {
      provide: LOCALE_ID,
      useValue: "pt-BR"
    }
],
  bootstrap: [AppComponent]
})
export class AppModule { }
