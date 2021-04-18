/** @author Jyothi Reddy Guduru , Lasya Bentula
 * @modifiedon 11/9/2020 
*/
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';
import { CommonModule } from "@angular/common";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BooksComponent } from './components/masters/books/books.component';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';

import { FindBookComponent } from './components/find-book/find-book.component';
import { ReturnBookComponent } from './components/return-book/return-book.component';
import { ReturnPolicyComponent } from './components/masters/return-policy/return-policy.component';
import { FinePolicyComponent } from './components/masters/fine-policy/fine-policy.component';
import { TextTransformPipe } from 'src/assets/pipes/text-transform-pipe';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { BookHistoryComponent } from './components/book-history/book-history.component';
import { UsersComponent } from './components/masters/users/users.component';

@NgModule({
  declarations: [
    AppComponent,
    BooksComponent,
    LoginComponent,
    FindBookComponent,
    ReturnBookComponent,
    ReturnPolicyComponent,
    FinePolicyComponent,
    TextTransformPipe,
    UserProfileComponent,
    BookHistoryComponent,
    UsersComponent
  ],
  imports: [
    BrowserModule,CommonModule,
    FormsModule,
    AppRoutingModule,
    ModalModule.forRoot(),
    NgxDatatableModule,
    HttpClientModule
  ],
  providers: [TextTransformPipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
