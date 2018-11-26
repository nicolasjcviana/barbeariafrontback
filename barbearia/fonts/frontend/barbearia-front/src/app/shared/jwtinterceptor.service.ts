import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // add authorization header with jwt token if available
        let token = localStorage.getItem('token');
        if (token) {
            request = request.clone({
                setHeaders: {
                    "Authorization": `Bearer ${token}`,
                    "Content-Type": 'application/json;charset=UTF-8'
                }
            });
        }

        return next.handle(request);
    }
}
