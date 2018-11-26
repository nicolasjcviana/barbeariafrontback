import { UtilsService } from './../commons/utils.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConfigs } from './../commons/app-configs';
import { Atendimento } from './atendimento';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AtendimentoService {

  constructor(private http : HttpClient,
    private utils : UtilsService) { }

    save(atendimento : any) {
      const headers = this.utils.getDefaultHeader();

      let atendimentoInserir : any = {};

      atendimentoInserir.cdFuncionario = atendimento.funcionarioSelecionado;
      atendimentoInserir.cdCliente = atendimento.clienteSelecionado;
      atendimentoInserir.itensAtendimento = [];
      atendimento.servicos.forEach(o => {
        let itemAtendimento : any = {};
        itemAtendimento.cdServico = o;
        itemAtendimento.vlServico = o.vlServico;
        atendimentoInserir.itensAtendimento.push(itemAtendimento);
      });

      let ate = JSON.stringify(atendimentoInserir);
      return this.http.post(this.getUrl(), ate, {headers}).toPromise();

    }

    getAll() : Promise<any>{
      return this.http.get(this.getUrlEnd("atendimentosCliente"))
      .toPromise()
      .then(resp => {
        return resp;
      });
    }

    getAtendimento(id) : Promise<any> {
      return this.http.get(this.getUrlComId(id))
      .toPromise()
      .then(resp => {
        return resp;
      });
    }

    getTotalReceitas() : any {
      return this.http.get(this.getUrl() + "/totalReceitas")
      .toPromise()
      .then(resp => {
        return resp;
      })
    }

    getTotalDespesas() : any {
      return this.http.get(this.getUrl() + "/totalDespesas")
      .toPromise()
      .then(resp => {
        return resp;
      })
    }

    getLucroTotal() : any {
      return this.http.get(this.getUrl() + "/lucroTotal")
      .toPromise()
      .then(resp => {
        return resp;
      })
    }
  
    getUrl() {
      return AppConfigs.API_ENDPOINT + "/atendimentos";
    }

    getUrlEnd(endpoint : String) {
      return AppConfigs.API_ENDPOINT + "/" + endpoint;
    }

    getUrlComId(id) {
      return AppConfigs.API_ENDPOINT + "/atendimentos/" + id ;
    }

    delete(id) {
      return this.http.delete(this.getUrlComId(id))
      .toPromise();
    }

  }
