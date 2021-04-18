import { Component, OnInit } from '@angular/core';
import { AppConfigService } from 'src/app/services/app-config.service';
import { GlobalService } from 'src/app/services/global.service';
/**
 * @author Lasya Bentula
 * @LastModifiedOn 11/19/2020
 */
@Component({
  selector: 'app-fine-policy',
  templateUrl: './fine-policy.component.html',
  styleUrls: ['./fine-policy.component.css']
})

/*
* This component handles fine policy.
*/
export class FinePolicyComponent implements OnInit {

  constructor(private appConfigService : AppConfigService, private globals:GlobalService) { }

  rows = [];
  isEdit = false;
  isAdd = false;
  isView = false;
  submitLabel = "";
  modalHeader = "";
  rule = {
    'name':null,
    'label':null,
    'finePerDay': null,
    'userType':null
  }

  columns: any = [
    { name: 'User Type', prop: 'userType' },
    { name: 'Rule', prop: 'label' },
    { name: 'Fine Per Day', prop: 'finePerDay' }
  ];
  showIssueBtn = true;
  ngOnInit(): void {
    this.appConfigService.getFinePolicy().subscribe((response : any)=>{
        this.rows = response;
   })
    var value =
      [{ 'name': "1sday", 'label': "1 Day", 'userType': "STUDENT", 'finePerDay':5},
      { 'name': "1<sday<5", 'label': "1 < Day < 5", 'userType': "STUDENT", 'finePerDay':10},
      { 'name': "5<=sday<10", 'label': "5 <= Day < 10", 'userType': "STUDENT", 'finePerDay':15},
      { 'name': "sday>=10", 'label': "Day >= 10", 'userType': "STUDENT", 'finePerDay':20},
      { 'name': "1fday", 'label': "1 Day", 'userType': "FACULTY", 'finePerDay':2},
      { 'name': "1<fday<5", 'label': "1 < Day < 5", 'userType': "FACULTY", 'finePerDay':5},
      { 'name': "5<=fday<10", 'label': "5 <= Day < 10", 'userType': "FACULTY", 'finePerDay':10},
      { 'name': "fday>=10", 'label': "Day >= 10", 'userType': "FACULTY", 'finePerDay':12}];


   // this.rows = value;
    if(this.globals.isAuthenticated()) {
      var user = this.globals.getLoggedInUser();
    //this.user.role = this.role;//assume
    if(user.userType == "LIBRARIAN") {
     // this.title = "Find";
      this.showIssueBtn = false;
    } else {
      //this.title = "Issue";
      this.showIssueBtn = true;
    }
    }
  }

  edit = function(event:any) {
    this.isEdit = true;
    this.isAdd = false;
    this.rule = Object.assign({}, event);
    this.submitLabel = "Submit";
    this.modalHeader = "Edit";
  }

  add = function() {
    this.isAdd = true;
    this.isEdit = false;
    this.modalHeader = "Add";
    this.submitLabel = "Submit";
    this.rule = {
      'name':null,
      'label':null,
      'finePerDay': null,
      'userType':null
    }
  }

  clear = function() {
    this.isAdd = false;
    this.isEdit = false;
    this.rule = {
      'name':null,
      'label':null,
      'finePerDay': null,
      'userType':null
    }
  }

  submit = function() {
    if(this.isAdd) {
        this.appConfigService.addRule(this.rule).subscribe((value:any)=> {
           this.appConfigService.getReturnPolicy().subscribe((response : any)=>{
            this.rows = response;
          }) 
        })
    } else if(this.isEdit) {
      this.appConfigService.editFineRule(this.rule).subscribe((value:any)=> {
        this.appConfigService.getFinePolicy().subscribe((response : any)=>{
         this.rows = response;
       }) 
     })
    }
  }

}
