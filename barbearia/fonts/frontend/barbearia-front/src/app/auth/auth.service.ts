import { UtilsService } from './../commons/utils.service';
import { AppConfigs } from './../commons/app-configs';
import { Http } from '@angular/http';
import { Headers } from '@angular/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({providedIn: 'root'})
export class AuthService {
  constructor(public jwtHelper: JwtHelperService,
              private http : Http,
              private utils : UtilsService ) {
    this.carregarToken();
  }

  jwtPayload : any;

  login(usuario: string, senha: string): Promise<void> {
    const headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    headers.append('Authorization', 'Basic YW5ndWxhcjpAbmd1bEByMA==');

    const body = `username=${usuario}&password=${senha}&grant_type=password`;

    return this.http.post(this.getUrl(), body, { headers })
      .toPromise()
      .then(response => {
        this.armazenarToken(response.json().access_token);
      })
      .catch(response => {
        if (response.status === 400) {
          const responseJson = response.json();

          if (responseJson.error === 'invalid_grant') {
            return Promise.reject('Usuário ou senha inválida!');
          }
        }

        return Promise.reject(response);
      });
  }


  private armazenarToken(token : string) {
    this.jwtPayload = this.jwtHelper.decodeToken(token);
    localStorage.setItem('token', token);
  }

  private carregarToken() {
    const token = localStorage.getItem('token');

    if (token) {
      this.armazenarToken(token);
    }
  }

  obterNovoAccessToken(): Promise<void> {
    const headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    headers.append('Authorization', 'Basic YW5ndWxhcjpAbmd1bEByMA==');

    const body = 'grant_type=refresh_token';

    return this.http.post(this.getUrl(), body,
        { headers, withCredentials: true })
      .toPromise()
      .then(response => {
        this.armazenarToken(response.json().access_token);

        console.log('Novo access token criado!');

        return Promise.resolve(null);
      })
      .catch(response => {
        console.error('Erro ao renovar token.', response);
        return Promise.resolve(null);
      });
  }

  limparAccessToken() {
    localStorage.removeItem('token');
    this.jwtPayload = null;
  }
  isAccessTokenInvalido() {
    const token = localStorage.getItem('token');
    return !token || this.jwtHelper.isTokenExpired(token);
  }

  logout() {
    const headers = new Headers();
     let token = localStorage.getItem('token');
     headers.append('Authorization', `Bearer ${token}`);
    return this.http.delete(this.getUrlLogout(),{headers})
      .toPromise()
      .then(() => {
        this.limparAccessToken();
      });
  }

  getUrl() {
    return  "http://localhost:8080/oauth/token";
  }

   getUrlLogout() {
    return  "http://localhost:8080/tokens/revoke";
  }
}
