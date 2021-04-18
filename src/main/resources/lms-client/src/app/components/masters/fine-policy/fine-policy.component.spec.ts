import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinePolicyComponent } from './fine-policy.component';

/**
 * @author Lasya Bentula
 * @LastModifiedOn 11/19/2020
 */
describe('FinePolicyComponent', () => {
  let component: FinePolicyComponent;
  let fixture: ComponentFixture<FinePolicyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FinePolicyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FinePolicyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
