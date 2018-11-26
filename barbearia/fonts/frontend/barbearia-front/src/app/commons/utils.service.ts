import { FormGroup } from '@angular/forms';
import { HttpHeaders } from '@angular/common/http';

import { Injectable } from '@angular/core';
import * as moment from 'moment';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor() { }

  formatarDataServico (data : String) {
    var datas = data.split("/");
    return `${datas[2]}-${datas[1]}-${datas[0]}`;
  }

  formatarDataTela (data : String ) {
    var datas = data.split("-");
    return `${datas[2]}/${datas[1]}/${datas[0]}`;
  }

  getDefaultHeader() : HttpHeaders {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return headers;
  }

  fillForm(form : FormGroup, obj : any) {
    Object.keys(obj).forEach(k => {
      if (form.controls[k]) {
        form.controls[k].setValue(obj[k]);
      }
    });
  }
}
