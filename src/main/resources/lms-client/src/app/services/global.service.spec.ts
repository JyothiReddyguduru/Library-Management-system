import { TestBed } from '@angular/core/testing';

import { GlobalService } from './global.service';
/** @author Jyothi Reddy Guduru , Lasya Bentula
 * @modifiedon 11/9/2020 
*/
describe('GlobalService', () => {
  let service: GlobalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GlobalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
