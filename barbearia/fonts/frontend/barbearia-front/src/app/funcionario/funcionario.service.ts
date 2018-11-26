import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UtilsService } from './../commons/utils.service';
import { AppConfigs } from './../commons/app-configs';
import { Funcionario } from './funcionario';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FuncionarioService {

  constructor(private http : HttpClient,

    private utils : UtilsService) { }

    save(funcionario : any) {
      const headers = this.utils.getDefaultHeader();
      let func = JSON.stringify(funcionario);
      if (funcionario.cdFuncionario) {
        return this.http.put(this.getUrlComId(funcionario.cdFuncionario), func, {headers}).toPromise();
      } else {
        return this.http.post(this.getUrl(), func, {headers}).toPromise();
      }
    }

    getAll() : Promise<any>{
      return this.http.get(this.getUrl())
      .toPromise()
      .then(resp => {
        return resp;
      });
    }

    getFuncionario(id) : Promise<any> {
      return this.http.get(this.getUrlComId(id))
      .toPromise()
      .then(resp => {
        return resp;
      });
    }

    getUrl() {
      return AppConfigs.API_ENDPOINT + "/funcionarios";
    }

    getUrlComId(id) {
      return AppConfigs.API_ENDPOINT + "/funcionarios/" + id ;
    }

    delete(id) {
      return this.http.delete(this.getUrlComId(id))
      .toPromise();
    }

  }
