import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GlobalService } from './services/global.service';

/** @author Jyothi Reddy Guduru , Lasya Bentula
 * This services handles all global events.
 * @modifiedon 11/9/2020 
*/

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

/*
* Handles actions realted to login and roles. 
*/

export class AppComponent {
  title = 'lms-client';
  username = "jyothiguduru";
  histItemtitle = "My Book History";
  findBooktitle = "Find Book";
  role = "STUDENT";
  user = {
    username : "",
    fullName : "",
    emailAddress : "",
    roles: []
  };
  isAuthenticated =  true;
  constructor(private globals : GlobalService,private route: ActivatedRoute,
    private router: Router) { }

  showLoginForm = function (){
    if(this.globals.isAuthenticated()) {
      //debugger;
     // this.user = this.globals.getLoggedInUser();
      //debugger
      
     /*if(this.hasRole['STUDENT'] || this.hasRole['FACULTY']) {
        this.histItemtitle = "My Book History";
        this.findBooktitle = "Find Book";
      } else {
        this.histItemtitle = "Book History";
        this.findBooktitle = "Issue Book";
      }*/
        return true;
    } else {
        //this.user = this.globalService.getLoggedInUser();
       
        return false;
    }
  }

  hasRole = function (role: string) {
    var user = this.globals.getLoggedInUser();
    if(!user) {
        return;
    }
    var roles = [];
    if (user &&user.roles.length>1) {
          roles = user.roles;
          return roles.indexOf(role) != -1;
    }
  }

  hasType = function (role: string) {
    var user = this.globals.getLoggedInUser();
    if(!user) {
        return;
    }
  //debugger
    return user.userType == role;
  }

  OnLogin = function() {
    var user = this.globals.getLoggedInUser();
    //debugger
    this.username = user.username;
    if(this.hasRole('USER')) {
      this.histItemtitle = "My Book History";
      this.findBooktitle = "Find Book";
    } else {
      this.histItemtitle = "Book History";
      this.findBooktitle = "Issue Book";
    }
  }
  


  logout = function() {
    this.globals.setAuthenticated(false);
    this.globals.setLoggedInUser(null);
    this.router.navigate(['login']);
  }


}
