import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable, NgModule } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import HttpErrorMensagem from '../model/http-erro';
import MensagemErro from '../model/mensagem-erro';

@Injectable({
  providedIn: 'root'
})
export class HandlerInterceptorService implements HttpInterceptor {

  constructor() { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(req).pipe(
      catchError((err: HttpErrorMensagem<MensagemErro>) => {

        const erroArray: string[] = [];

        const erros:any = err.error?.objects;

        erros.forEach((element: { userMessage: string; }) => {
          erroArray.push(element.userMessage)
        });

        console.log(erroArray)



        const log = 'Título: ' + err?.error?.title + '\nDetalhe: ' + err.error?.detail + '\nErros: \n' + erroArray.join(".\n") ;

        alert(log);
        console.log(err);
        return throwError(err);

      }));
  }



  }

  @NgModule({
    providers: [{
      provide: HTTP_INTERCEPTORS,
      useClass: HandlerInterceptorService,
      multi: true,
    },
  ],
  })

  @Injectable({
    providedIn: 'root',
  })

export class HttpInterceptorModule {}
