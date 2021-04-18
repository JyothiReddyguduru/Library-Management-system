import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
/** @author Jyothi Reddy Guduru , Lasya Bentula
 * @modifiedon 11/9/2020 
*/
@Injectable({
  providedIn: 'root'
})

/*
* Endpoint realted to book return policy, fine policy and other config.
*/
export class AppConfigService {

  constructor(private http: HttpClient) { }

  getReturnPolicy(): Observable<any>{
    return this.http.get("/appConfig/returnPolicy");
  }

  getBookLimitPolicy() :  Observable<any>{
    return this.http.get("/appConfig/getBookLimitPolicy");
  }

  getFinePolicy() :  Observable<any>{
    return this.http.get("/appConfig/getfinePolicy");
  }

  editBookLimitRule(rule:any): Observable<any> {
    return this.http.post('/appConfig/editBookLimitRule', rule);
  }

  editFineRule(rule:any): Observable<any> {
    return this.http.post('/appConfig/editFineRule', rule);
  }
}
