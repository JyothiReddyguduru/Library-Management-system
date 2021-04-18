import { TestBed } from '@angular/core/testing';

import { AppConfigService } from './app-config.service';

/** @author Jyothi Reddy Guduru , Lasya Bentula
 * @modifiedon 11/9/2020 
*/
describe('AppConfigService', () => {
  let service: AppConfigService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppConfigService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
