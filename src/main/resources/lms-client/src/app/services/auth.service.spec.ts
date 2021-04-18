import { TestBed } from '@angular/core/testing';

import { AuthService } from './auth.service';

/** @author Jyothi Reddy Guduru 
 * @modifiedon 11/9/2020 
*/
describe('AuthService', () => {
  let service: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
