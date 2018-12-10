import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UtilsService } from './../commons/utils.service';
import { AppConfigs } from './../commons/app-configs';
import { Injectable } from '@angular/core';
import { Md5 } from 'ts-md5';
import * as CryptoJS from 'crypto-js';

@Injectable({
  providedIn: 'root'
})
export class FuncionarioService {

  constructor(private http : HttpClient,

    private utils : UtilsService) { }

    save(funcionario : any) {

      var key = CryptoJS.enc.Utf8.parse('7061737323313233');
      var iv = CryptoJS.enc.Utf8.parse('7061737323313233');
      var encrypted = CryptoJS.AES.encrypt(CryptoJS.enc.Utf8.parse(funcionario.nmFuncionario + funcionario.dsEmail), key,
          {
              keySize: 128 / 8,
              iv: iv,
              mode: CryptoJS.mode.CBC,
              padding: CryptoJS.pad.Pkcs7
          });

      var decrypted = CryptoJS.AES.decrypt(encrypted, key, {
          keySize: 128 / 8,
          iv: iv,
          mode: CryptoJS.mode.CBC,
          padding: CryptoJS.pad.Pkcs7
      });

      console.log('Encrypted :' + encrypted);
      console.log('Key :' + encrypted.key);
      console.log('Salt :' + encrypted.salt);
      console.log('iv :' + encrypted.iv);
      console.log('Decrypted : ' + decrypted);
      console.log('utf8 = ' + decrypted.toString(CryptoJS.enc.Utf8));

      let headers = this.utils.getDefaultHeader();
      headers = headers.append('Hash', `${encrypted}`);      
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
