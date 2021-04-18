import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/services/book.service';
/**
 * @author Lasya Bentula 
 * @LastModifiedOn 11/10/2020
 */
@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})

/*
* Books componet. This class handles all the actions (view, add, edit) related to books.
*/
export class BooksComponent implements OnInit {

  rows = [];
  book = {
    name : null,
    isbn : null,
    copies: null,
    author:null
  };
  isEdit = false;
  isAdd = false;
  isView = false;
  modalHeader = "Add";
  columns: any = [
    { name: 'Book Name', prop: 'name' },
    { name: 'Author', prop: 'author' },
    { name: 'ISBN', prop: 'isbn' },
    { name: 'Total Copies', prop: 'copies.length'}
  ];

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.getBooks();//code at bottom
  }

  /*
  * Function that handles edit books
  */
  edit = function (event: any): void {
    this.book = Object.assign({}, event);
    this.isEdit = true;
    this.isAdd = false;
    this.isView = false;
    this.modalHeader = "Edit";
  }

  
  /*
  * Function that handles view books
  */
  view = function (event: any): void {
    this.book = Object.assign({}, event);
    this.isEdit = false;
    this.isView = true;
    this.isAdd = false;
    this.modalHeader = "View";
  }

  /*
  * Function that handles adding of books
  */
  add = function (): void {
    this.book = {
      name : null,
      isbn : null,
      copies: null,
      author:null
    };
    this.isEdit = false;
    this.isAdd = true;
    this.isView = false;
    this.modalHeader = "Add";
  }

  
  getBooks = function() {
    this.bookService.getBooks().subscribe((value) => {debugger
      this.rows = value;
    }, (error : any) => {debugger
    })
  }

  /*
  * Function that handles submission of books
  */
  onSubmit = function(data : any) {
    if(this.isAdd) {//add book
      this.bookService.addBook(this.book).subscribe((value) => {

        this.getBooks();//reload books
      }, (error : any) => {
      })
    } else if (this.isEdit) {//edit book
      if(Array.isArray(this.book.copies)){
        this.book.copies = this.book.copies.length;
      }
      
      this.bookService.editBook(this.book).subscribe((value) => {
        this.getBooks();//reload books
      }, (error : any) => {
      })
    }
    this.isEdit = false;
    this.isAdd = false;
    this.isView = false;
  }

  onClear = function() {
    this.isEdit = false;
    this.isAdd = false;
    this.isView = false;  
    this.book = {
      name : null,
      isbn : null,
      copies: null,
      author:null
    };
  }
  

}
