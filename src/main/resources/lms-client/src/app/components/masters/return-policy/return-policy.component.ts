import { Component, OnInit } from '@angular/core';
import { AppConfigService } from 'src/app/services/app-config.service';
/**
 * @author Jyothi Reddy Guduru
 * @LastModifiedOn 11/10/2020
 */
@Component({
  selector: 'app-return-policy',
  templateUrl: './return-policy.component.html',
  styleUrls: ['./return-policy.component.css']
})
/*
* This component handles all the actions related to policy of  returning book.
*/
export class ReturnPolicyComponent implements OnInit {

  constructor(private appConfigService : AppConfigService) { }

  rows = [];
  isEdit = false;
  isAdd = false;
  isView = false;
  submitLabel = "";
  modalHeader = "";
  rule = {
    'totalLimit': null,
    'userType':null
  }

  columns: any = [
    { name: 'User Type', prop: 'userType' },
    { name: 'Borrowing Limit in days', prop: 'totalLimit' },
  ];

  ngOnInit(): void {
    this.appConfigService.getBookLimitPolicy().subscribe((response : any)=>{
        this.rows = response;
   })
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
      'days':null,
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
  if(this.isEdit) {
      this.appConfigService.editBookLimitRule(this.rule).subscribe((value:any)=> {
        this.appConfigService.getBookLimitPolicy().subscribe((response : any)=>{
         this.rows = response;
       }) 
     })
    }
  }
}
