import { UtilsService } from './../commons/utils.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConfigs } from './../commons/app-configs';
import { Servico } from './servico';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class ServicoService {

  constructor(private http : HttpClient,
    private utils : UtilsService) { }

    save(servico : any) {
      const headers = this.utils.getDefaultHeader();
      let serv = JSON.stringify(servico);
      if (servico.cdServico) {
        return this.http.put(this.getUrlComId(servico.cdServico), serv, {headers}).toPromise();
      } else {
        return this.http.post(this.getUrl(), serv, {headers}).toPromise();
      }
    }

    getAll() : Promise<any>{
      return this.http.get(this.getUrl())
      .toPromise()
      .then(resp => {
        return resp;
      });
    }

    getServico(id) : Promise<any> {
      return this.http.get(this.getUrlComId(id))
      .toPromise()
      .then(resp => {
        return resp;
      });
    }

    listarServicosPorNome(nome) {
      return this.http.get(this.getUrlComNome(nome))
      .toPromise()
      .then(resp => {
        return resp;
      });
    }

    getUrl() {
      return AppConfigs.API_ENDPOINT + "/servicos";
    }

    getUrlComId(id) {
      return AppConfigs.API_ENDPOINT + "/servicos/" + id ;
    }

    getUrlComNome(nome) {
      return AppConfigs.API_ENDPOINT + "/servicosPorNome/" + nome ;
    }

    delete(id) {
      return this.http.delete(this.getUrlComId(id))
      .toPromise();
    }

  }
