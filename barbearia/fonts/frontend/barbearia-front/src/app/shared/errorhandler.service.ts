import { Router } from '@angular/router';
import { AuthService } from './../auth/auth.service';
import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';


@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthService,
                private router : Router) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError(err => {
            if (err.status === 401) {
                // auto logout if 401 response returned from api
                this.authenticationService.logout()
                .then(o => {
                  this.router.navigate(['login']);
                  location.reload(true);
                }).catch( o => {

                //this.authenticationService.logout();
                })

            }

            const error = err.error.message || err.statusText;
            return throwError(error);
        }))
    }
}
