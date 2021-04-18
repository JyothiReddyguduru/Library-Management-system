/** @author Jyothi Reddy Guduru , Lasya Bentula
 * @modifiedon 11/9/2020 
*/
import { Router, ActivatedRoute } from '@angular/router';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

  /*
  * Login component. This class handles all the actions related login.
  */

export class LoginComponent implements OnInit {

  @Output() loginEmitter: EventEmitter<string> = new EventEmitter<string>();

  constructor(private authService: AuthService, private route: ActivatedRoute,
    private router: Router, private globals: GlobalService) { }
  user = { username: '', password: '' };
  signinuser = { username: '', password: '', emailAddress: '', repassword: '', userType: 'NULL' };
  showSigninErrorMsg = false;
  showLoginErrorMsg = false;
  showSigninSuccessMsg = false;
  showLogin = true;
  errorMessage = "";
  SuccessMessage = "";
  isSubmitted: boolean = false;
  invalidCredentials: boolean = false;
  errorMessages: any = {
    emptyPassword: "Username and Password is required",
    empty: "Please enter all details",
    invalidCredentials: "Invalid credentials",
    regsuccess: "Registered Successfully",
    unknownError: "Unknown error occurred. Please try logging in after sometime"
  }
  ngOnInit(): void {
  }

  handleErrorMessages = function () {
    if (this.isSubmitted && this.invalidCredentials) {
      this.invalidCredentials = false;
    }
  }

  showIsRequiredMessage = function (field: any) {
    if (this.isSubmitted && field.errors && (field.invalid || field.dirty || !field.touched)) {
      this.message = this.errorMessages.emptyPassword;
      return true;
    } else {
      return false;
    }
  }

  // areCredentialsInvalid = function (username: any, password: any) {
  //   if (this.isSubmitted && username.valid && password.valid && this.invalidCredentials) {
  //     this.message = this.errorMessages.invalidCredentials;
  //     return true;
  //   } else {
  //     return false;
  //   }
  // }

  register = function () {
    if (!this.signinuser.username || !this.signinuser.password ||
      !this.signinuser.repassword || !this.signinuser.emailAddress || !this.signinuser.userType || this.signinuser.userType == 'NULL') {
      this.showSigninErrorMsg = true;
      this.showSigninSuccessMsg = false;
      this.errorMsg = this.errorMessages.empty;
      return;
    } else {
      this.authService.addUser(this.signinuser).subscribe(response => {
        this.showSigninErrorMsg = false;
        this.showSigninSuccessMsg = true;
        this.successmessage = this.errorMessages.regsuccess;
      }, error => {
        this.showSigninErrorMsg = true;
        this.showSigninSuccessMsg = false;
        this.errorMsg = this.errorMessages.unknownError;
      })

    }
  }

  showSignupForm = function () {
    this.showLogin = false;
    this.showSigninErrorMsg = false;
    this.showLoginErrorMsg = false;
    this.showSigninSuccessMsg = false;
  }

  closeSignupForm = function () {
    this.showLogin = true;
    this.showSigninErrorMsg = false;
    this.showLoginErrorMsg = false;
    this.showSigninSuccessMsg = false;
  }

  logIn = function (): void {
    if (!this.user.username || !this.user.password) {
      this.showLoginErrorMsg = true;
      this.loginErrorMsg = this.errorMessages.emptyPassword;
      return;
    } else {
      this.isSubmitted = true;
      if (this.user.username && this.user.password) {
        this.authService.login(this.user).subscribe((response: any) => {
          debugger
          this.loginEmitter.emit(response);
          // this.globals.setAuthenticated(true);
          this.showLoginErrorMsg = false;
          this.loginErrorMsg = "";
          this.isSubmitted = false;
          // this.loginForm.reset();
          this.globals.setAuthenticated(true);
          // this.router.navigate(['findBook']);
          this.user.username = "";
          this.user.password = "";
          var user = this.globals.getLoggedInUser();
          if (user.userType == 'STUDENT' || user.userType == 'FACULTY') {
            this.router.navigate(['findBook']);

          } else if (user.userType == 'ADMIN') {
            this.router.navigate(['/manageBooks']);
          } else {
            this.router.navigate(['findBook']);
          }
        }, (error: any) => {
          debugger;
          this.loginErrorMsg = this.errorMessages.invalidCredentials;
          this.showLoginErrorMsg = true;
          /* if (error.status == 401) {
             this.invalidCredentials = true;
           } else if(error.error && error.error.showMessage && error.error.errorMessage) {
             this.isError = true;
             this.message = error.error.errorMessage;
           } else {
             this.isError = true;
             this.message = this.errorMessages.unknownError;
           }
           this.isLoading = false;    
           this.loginEmitter.emit(error);
         })*/
        });
        //this.globals.setAuthenticated(true);
        // this.router.navigate(['findBook']);




      };
    }
  }
}
