import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { GlobalService } from 'src/app/services/global.service';
import { BookService } from 'src/app/services/book.service';

/** @author Jyothi Reddy Guduru 
 * @modifiedon 11/9/2020 
*/
@Component({
  selector: 'app-book-history',
  templateUrl: './book-history.component.html',
  styleUrls: ['./book-history.component.css']
})
export class BookHistoryComponent implements OnInit {

  title = "My Book History";
  role = "STUDENT";
  rows = [];
  columns: any = [
    { name: 'Copy Id', prop: 'bookCopyId' },
    { name: 'Book Name', prop: 'name' },
    { name: 'Author', prop: 'author' },
    { name: 'ISBN', prop: 'isbn' },
    { name: 'Issued To', prop: 'borrower' },
    { name: 'Issued Date', prop: 'action_date' },
    { name: 'Return Date', prop: 'returnDate' },
    { name: 'Due Date', prop: 'due_date' },
    { name: 'Fine in $', prop: 'fine' },
  ];
  user = {
    username: "",
    fullName: "",
    emailAddress: "",
    roles: [],
    userType: ""
  };
  constructor(private globalService: GlobalService, private bookService: BookService) {
  }

  ngOnInit(): void {
    //Validate if the user is authenticated and get the book history
    if (this.globalService.isAuthenticated()) {
      this.user = this.globalService.getLoggedInUser();
      
      if (this.user.userType == "STUDENT" || this.user.userType == "FACULTY") {
        this.title = "My Book History";
      
        this.bookService.getMyBookHistory(this.user.username).subscribe((value: any) => {
          this.rows = value;
        });
      } else {
        this.title = "Book History";
        //var user = this.globalService.getLoggedInUser();
        this.bookService.getBookHistory().subscribe((value: any) => {
          this.rows = value;
        });
      }


    }
    var pipe = new DatePipe('en-US');
    var date = new Date();
    var myFormattedDate = pipe.transform(date, 'M/d/yy');

  }

}
