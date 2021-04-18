/** @author Jyothi Reddy Guduru , Lasya Bentula
 * @modifiedon 11/9/2020 
*/
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

/** @author Lasya Bentula 
 * @modifiedon 11/10/2020 
*/

@Injectable({
  providedIn: 'root'
})


/*
* Endpoint related to books; Ex: history, view all books, issuebook.
*/
export class BookService {

  constructor(private http: HttpClient) { }

  getBooks(): Observable<any>{//apply filters later
    return this.http.get("/books/all");
  }

  getMyBooks(username : string): Observable<any>{
    return this.http.get("/mybooks");
  }

  getMyBookHistory(username : string): Observable<any>{
    return this.http.get("/myHistory?name="+username);
  }
  getBookHistory(): Observable<any>{
    return this.http.get("/history");
  }

  getIssuedBooks(): Observable<any>{//apply filters later
    return this.http.get("/books/issued");
  }

  getFineForCopy(username : string, isbn:string): Observable<any>{
    return this.http.get("bookCopy/fine?username="+username +"&isbn=" + isbn);
  }

  issueBook(username : string, isbn:string): Observable<any> {
    var par = {
      'username' : username,
      'isbn':isbn
    }
    return this.http.post('/issueBook', par);
  }

  returnBook(username : string, isbn:string): Observable<any> {
    var par = {
      'username' : username,
      'isbn':isbn
    }
    return this.http.post('/returnBook', par);
  }

  addBook(book:any): Observable<any> {
    console.log("Abhinav in add")
    
    return this.http.post('/addBook', book);
  }

  editBook(book:any): Observable<any> {
    return this.http.post('/editBook', book);
  }

}
