import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BooksComponent } from './components/masters/books/books.component';
import { LoginComponent } from './components/login/login.component';
import { FindBookComponent } from './components/find-book/find-book.component';
import { ReturnBookComponent } from './components/return-book/return-book.component';
import { ReturnPolicyComponent } from './components/masters/return-policy/return-policy.component';
import { FinePolicyComponent } from './components/masters/fine-policy/fine-policy.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { BookHistoryComponent } from './components/book-history/book-history.component';
import { UsersComponent } from './components/masters/users/users.component';

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "findBook", component: FindBookComponent},
  { path: "returnBook", component: ReturnBookComponent},
  { path: "manageBooks", component: BooksComponent },
  { path: "returnPolicy", component: ReturnPolicyComponent },
  { path: "finePolicy", component: FinePolicyComponent },
  { path: "profile", component : UserProfileComponent},
  { path: "bookHistory", component : BookHistoryComponent},
  { path: "users", component : UsersComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
