import { AppConstants } from './../app-constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AeronaveService {
  constructor(private http: HttpClient) {}

  getAeronaveList(): Observable<any> {
    return this.http.get<any>(AppConstants.baseUrl);
  }

  deleteAeronave(id: Number): Observable<any> {
    return this.http.delete(AppConstants.baseUrl + id, {
      responseType: 'text',
    });
  }

  getAeronave(id: any): Observable<any> {
    return this.http.get<any>(AppConstants.baseUrl + id);
  }

  salvarAeronave(aeronave: any): Observable<any> {
    return this.http.post<any>(AppConstants.baseUrl, aeronave);
  }

  updateAeronave(id:any,aeronave: any): Observable<any> {
   // delete aeronave.id;
 //  aeronave.id = null;
    return this.http.put<any>(AppConstants.baseUrl + id, aeronave);
  }

  getAeronaveSemanal(): Observable<{ semanal: number }> {
    return this.http.get<{ semanal: number }>(
      AppConstants.baseUrl + 'registro-semanal'
    );
  }

  getAeronaveDecada(): Observable<any> {
    return this.http.get<any>(AppConstants.baseUrl + 'decada');
  }
  getAeronavMarcaQuantidade(): Observable<any> {
    return this.http.get<any>(AppConstants.baseUrl + 'marca-quantidade');
  }

  getAeronaveNaoVendida(): Observable<{ disponiveis: number }> {
    return this.http.get<{ disponiveis: number }>(
      AppConstants.baseUrl + 'nao-vendidas'
    );
  }

  consultarModelo(nome: String): Observable<any> {
    return this.http.get(AppConstants.baseUrl + 'find/' + nome);
  }

  getAeronaveListPage(pagina: any): Observable<any> {
    return this.http.get<any>(AppConstants.baseUrl + 'page?page=' + pagina);
  }

  consultarAeronavePorPage(nome: String, pagina: Number): Observable<any> {
    return this.http.get(
      AppConstants.baseUrl + 'find/' + nome + '/page?page=' + pagina
    );
  }



pdfRelatorioSemanal() {
    return this.http.get(AppConstants.baseUrl + 'relatorio/semanal', {responseType : 'text'}).subscribe(data => {
      const iframe = document.querySelector('iframe');
      iframe?.setAttribute('src', data);
    });
  }

PdfRelatorioDisponiveis() {
    return this.http.get(AppConstants.baseUrl + 'relatorio/nao-vendidas', {responseType : 'text'}).subscribe(data => {
      const iframe = document.querySelector('iframe');
      iframe?.setAttribute('src', data);
    });
  }


pdfRelatorioVendidas() {
  return this.http.get(AppConstants.baseUrl + 'relatorio/vendidas', {responseType : 'text'}).subscribe(data => {
    const iframe = document.querySelector('iframe');
    iframe?.setAttribute('src', data);
  });
}

}
