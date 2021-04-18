import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { TextTransformPipe } from 'src/assets/pipes/text-transform-pipe';
import { BookService } from 'src/app/services/book.service';
/**
 * @author Lasya Bentula
 * @LastModifiedOn 11/10/2020
 */
@Component({
  selector: 'app-return-book',
  templateUrl: './return-book.component.html',
  styleUrls: ['./return-book.component.css']
})

/*
* This component is used to handle all actions realted to returning of book.
*/
export class ReturnBookComponent implements OnInit {
  rows = [];
  isView = false;
  submitLabel = "";
  book = {
    name: null,
    isbn: null,
    copies: null,
    author: null,
    dueDate: null,
    fine: 0,
    issuedDate: null
  };
  isReturn = false;
  isIssue = false;
  modalHeader = "";
  username = null;
  searchString = null;
  columns: any = [
    { name: 'Copy Id', prop: 'bookCopyId' },
    { name: 'Book Name', prop: 'name' },
    { name: 'Author', prop: 'author' },
    { name: 'ISBN', prop: 'isbn' },
    { name: 'Issued To', prop: 'borrower' },
    { name: 'Issued Date', prop: 'action_date' },
    { name: 'Due Date', prop: 'due_date' },

  ];
  constructor(private bookService: BookService) {
    var pipe = new DatePipe('en-US');
    var date = new Date();
    var myFormattedDate = pipe.transform(date, 'M/d/yy');
    
  }

  ngOnInit(): void {
     this.bookService.getIssuedBooks().subscribe((value) => {
       this.rows = value;
     }, (error : any) => {
     })
  }


  /*
  * This function used to handle search functionality
  */
  search = function (): void {
    this.isReturn = false;
    this.isIssue = false;
    //make a call to service to fetch books based on search filters
    this.clear();
  }

  /*
  * This function used to handle clear functionality
  */
  clear = function () {
    this.isReturn = false;
    this.isIssue = false;
    this.searchString = null;
    this.book = {
      name: null,
      isbn: null,
      copies: null,
      author: null,
      issuedTo: null,
      dueDate: null,
      fine: 0,
      issuedDate: null
    };
  }

  /*
  * This function used to handle issuing of bookfunctionality
  */
  getIssuedBooks = function () {
    this.bookService.getIssuedBooks().subscribe((value: any) => {
      this.rows = value;
    }, (error: any) => {
    })
  }

  return = function (event: any) {
    this.book = Object.assign({}, event);
    this.book.fine = 100;
    this.username = "Jyothi";
    this.submitLabel = "Return";
    this.isReturn = true;
    this.isIssue = false;
    this.book.copies = 1;
     this.bookService.getFineForCopy(this.username, this.book.isbn).subscribe((value: any) => {
       if (value) {
         this.book.fine = value;
         this.username = "Jyothi";
         this.submitLabel = "Return";
         this.isReturn = true;
         this.isIssue = false;
         this.book.copies = 1;
       }
     }, (error: any) => {

     })

  }

  /*
  * This function used to handle submit book functionality
  */
  submit = function () {
    if (this.isReturn) {
      this.bookService.returnBook().subscribe((value: any) => {
        this.bookService.getIssuedBooks().subscribe((value1: any) => {
          this.rows = value1;
        }, (error1: any) => {
        })
      }, (error2: any) => {
      })
    } else {

    }
  }
}