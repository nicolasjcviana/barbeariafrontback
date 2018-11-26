import { UtilsService } from './../commons/utils.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConfigs } from './../commons/app-configs';
import { Cliente } from './cliente';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http : HttpClient,
    private utils : UtilsService) { }

    save(cliente : any) {
      const headers = this.utils.getDefaultHeader();
      let cli = JSON.stringify(cliente);
      if (cliente.cdCliente) {
        return this.http.put(this.getUrlComId(cliente.cdCliente), cli, {headers}).toPromise();
      } else {
        return this.http.post(this.getUrl(), cli, {headers}).toPromise();
      }
    }

    getAll() : Promise<any>{
      return this.http.get(this.getUrl())
      .toPromise()
      .then(resp => {
        return resp;
      });
    }

    getCliente(id) : Promise<any> {
      return this.http.get(this.getUrlComId(id))
      .toPromise()
      .then(resp => {
        return resp;
      });
    }

    getUrl() {
      return AppConfigs.API_ENDPOINT + "/clientes";
    }

    getUrlComId(id) {
      return AppConfigs.API_ENDPOINT + "/clientes/" + id ;
    }

    delete(id) {
      return this.http.delete(this.getUrlComId(id))
      .toPromise();
    }

  }
