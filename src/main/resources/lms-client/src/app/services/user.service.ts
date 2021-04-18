import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
/** @author Jyothi Reddy Guduru
 * @modifiedon 11/27/2020 
*/
@Injectable({
  providedIn: 'root'
})
/*
*Handles endpoints related to adding user and general user information
*/
export class UserService {

  constructor(private http: HttpClient) { }

  addUser(user:any): Observable<any> {
    return this.http.post('/addUser', user);
  }

  getAllUsers(): Observable<any>{
    return this.http.get("/users");
  }}
