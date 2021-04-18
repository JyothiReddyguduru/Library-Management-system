/** @author Jyothi Reddy Guduru 
 * @modifiedon 11/9/2020 
*/

import { Observable } from 'rxjs';
import { GlobalService } from 'src/app/services/global.service';
import { Injectable, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

/*
* Endpoint realted to login and handling authentication.
*/
export class AuthService {

  @Output()  loginEmitter = new EventEmitter();

  authHeaders = {
    authorization : ""
  }

  constructor( private http:HttpClient, private globals:GlobalService) { }


    login = function (user: any): Observable<any> { 
      return this.authenticate(user); 
    }.bind(this);

     authenticate = function (user:any): Observable<any> {
      user.password = btoa(user.password);
      return new Observable(observer => {
          this.http.post('/user',user).subscribe(response => {debugger
              this.globals.setLoggedInUser(response);
              this.globals.setAuthenticated(true);
              observer.next(response);
          }, (error: any) => {
              observer.error(error);
          });
      });
  }.bind(this);
  
}
