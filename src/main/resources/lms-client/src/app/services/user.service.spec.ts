import { TestBed } from '@angular/core/testing';

import { UserService } from './user.service';

/** @author Jyothi Reddy Guduru
 * @modifiedon 11/27/2020 
*/
describe('UserService', () => {
  let service: UserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
