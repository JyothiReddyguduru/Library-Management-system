import { TestBed } from '@angular/core/testing';

import { BookService } from './book.service';

/** @author Lasya Bentula 
 * @modifiedon 11/10/2020 
*/
describe('BookService', () => {
  let service: BookService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BookService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
