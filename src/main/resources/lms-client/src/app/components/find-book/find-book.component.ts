/** @author Jyothi Reddy Guduru 
 * @modifiedon 11/9/2020 
*/
import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/services/book.service';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-find-book',
  templateUrl: './find-book.component.html',
  styleUrls: ['./find-book.component.css']
})
export class FindBookComponent implements OnInit {
  rows = [];
  isView = false;
  submitLabel = "";
  showError = false;
  book = {
    name : null,
    isbn : null,
    copies: null,
    author:null
  };
  isReturn = false;
  isIssue = false;
  modalHeader = "";
  username = null;
  searchString = null;
  title = "";
  columns: any = [
    { name: 'Book Name', prop: 'name' },
    { name: 'Author', prop: 'author' },
    { name: 'ISBN', prop: 'isbn' },
    { name: 'Total Copies', prop: 'copies'}
  ];
  showIssueBtn = true;
  constructor(private bookService: BookService, private globals : GlobalService) { }

  /*
  * Validate if the user is authenticated and get all the books
  */
  ngOnInit(): void {

    this.bookService.getBooks().subscribe((value:any)=> {
      this.getBooks();
      if(this.globals.isAuthenticated()) {
        var user = this.globals.getLoggedInUser();
      //this.user.role = this.role;//assume
      if(user.userType == "STUDENT" || user.userType == "FACULTY") {
        this.title = "Find";
        this.showIssueBtn = false;
      } else {
        this.title = "Issue";
        this.showIssueBtn = true;
      }
      }
     
    }, (error : any) => {
    })
   
  }


  /*
  * Function to view book data in the UI
  */
  view = function (event: any): void {
    this.book = Object.assign({}, event);
    this.isView = true;
    this.modalHeader = "View";
  }

  
  /*
  * Function to serach book data from the UI
  */
  search = function (): void {
    this.isReturn = false;
    this.isIssue = false;
    //make a call to service to fetch books based on search filters
    this.clear();
  }

  
  /*
  * Function to clear book data in the UI
  */
  clear = function() {
    this.isReturn = false;
    this.isIssue = false;
    this.searchString = null; 
    this.book = {
      name : null,
      isbn : null,
      copies: null,
      author:null, 
      issuedTo:null
    };
  }

  
  /*
  * Function to issue book data from the UI
  */
  issue = function(event: any) {
    this.submitLabel = "Issue";
    this.username = null;
    this.isReturn = false;
    this.isIssue = true;
    this.book = Object.assign({}, event);
    this.book.copies = 1;
    this. modalHeader = "Issue";
  }

  return = function(event: any) {
    this.username = "Jyothi";
    this.submitLabel = "Return";
    this.isReturn = true;
    this.isIssue = false;
    this.book = Object.assign({}, event);
    this.book.copies = 1;
    this. modalHeader = "Return";

  }

    
  /*
  * Function to check the roles of logged in user
  */
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

    
  /*
  * Function to get books data in the UI from the backend server
  */
  getBooks = function() {
    this.bookService.getBooks().subscribe((value:any)=> {
      this.rows = this.getAvaliableBooksCount(value);
    }, (error : any) => {
    })
  }


  /*
  * Function to submit books data to the backend server
  */
  submit = function() {
    this.bookService.issueBook(this.username, this.book.isbn).subscribe((value:any) => {
        this.getBooks();
    }, (error : any) => {
      this.showError = true;
    })
  }

  /*
  * Function to calculate avaliable books
  */
  getAvaliableBooksCount = function(value: any){
    for(var i = 0; i < value.length; i++){
      var count = 0;
      for(var j = 0; j <  value[i]["copies"].length; j++){
        if( value[i]["copies"][j].status == "AVAILABLE"){
          count++;
        }
      }
      delete value[i]["copies"];
      value[i]["copies"] = count;
      
    }
    return value;
  }
}
