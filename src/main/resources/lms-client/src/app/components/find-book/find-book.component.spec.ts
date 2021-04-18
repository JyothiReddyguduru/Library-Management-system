import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindBookComponent } from './find-book.component';

/** @author Jyothi Reddy Guduru 
 * @modifiedon 11/9/2020 
*/

describe('FindBookComponent', () => {
  let component: FindBookComponent;
  let fixture: ComponentFixture<FindBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FindBookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FindBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
