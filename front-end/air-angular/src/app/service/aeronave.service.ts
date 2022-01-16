import { AppConstants } from './../app-constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AeronaveService {

  constructor(private http: HttpClient) { }

  getAeronaveList(): Observable<any> {
      return this.http.get<any>(AppConstants.baseUrl);
  }

  deleteAeronave(id: Number): Observable<any>{
    return this.http.delete(AppConstants.baseUrl + id, {responseType : 'text'});
  }


}
