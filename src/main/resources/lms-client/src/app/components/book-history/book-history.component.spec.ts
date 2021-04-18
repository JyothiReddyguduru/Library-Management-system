import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookHistoryComponent } from './book-history.component';

/** @author Jyothi Reddy Guduru 
 * @modifiedon 11/9/2020 
*/

describe('BookHistoryComponent', () => {
  let component: BookHistoryComponent;
  let fixture: ComponentFixture<BookHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
