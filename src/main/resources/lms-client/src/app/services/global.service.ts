import { EventEmitter, Injectable, Output } from '@angular/core';
@Injectable({
  providedIn: 'root'
})

/** @author Jyothi Reddy Guduru , Lasya Bentula
 * This services handles all global events.
 * @modifiedon 11/9/2020 
*/

export class GlobalService {
  @Output() loggedInUserEmitter = new EventEmitter();

  hasBeenAuthenticated = false;
  constructor() {

   }

  setLoggedInUser = function (user: any) {
    this.loggedInUser = user;
    this.loggedInUserEmitter.emit(true);
}

getLoggedInUser = function () {
    return this.loggedInUser;
}

isAuthenticated = function () {
  return this.hasBeenAuthenticated;
}

setAuthenticated = function (isAuthenticated: boolean) {
  this.hasBeenAuthenticated = isAuthenticated;
}

hasRole = function (role: string) {debugger
  var roles = [];
  if(this.loggedInUser) {
    roles = this.loggedInUser.roles;
        return roles.indexOf(role) != -1;

  }
  //if (!this.isEmpty(this.loggedInUser)) {
        
  }

}
