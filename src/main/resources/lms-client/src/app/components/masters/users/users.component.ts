import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
/** @author Jyothi Reddy Guduru
 * @modifiedon 11/9/2020 
*/
@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})

/*
* This componet is used for actions realted to user.
*/
export class UsersComponent implements OnInit {

  rows = [];
  columns: any = [
    { name: 'username', prop: 'username' },
    { name: 'Full Name', prop: 'fullName' },
    { name: 'Email Address', prop: 'emailAddress' },
    { name: 'User Type', prop: 'userType' },
    { name: 'Status', prop: 'userStatus' }
  ];
  user = {
    username : "",
    fullName : "",
    emailAddress : "",
    role : ""
  };
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe((value: any) => {
      debugger
      this.rows = value;
    });
  }
 
}
