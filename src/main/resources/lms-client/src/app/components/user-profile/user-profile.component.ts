import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/services/global.service';
/** @author Jyothi Reddy Guduru
 * @modifiedon 11/9/2020 
*/
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})

/*
* This component is used to handle user profile actions
*/
export class UserProfileComponent implements OnInit {

 user = null;
  constructor(private globalService: GlobalService) { 
    this.user = {
      username : "",
      fullName : "",
      emailAddress : "",
      role : ""
    };
  }

  ngOnInit(): void {
    if(this.globalService.isAuthenticated()) {
        this.user = this.globalService.getLoggedInUser();//uncomment @Lasya
    }
}
}
