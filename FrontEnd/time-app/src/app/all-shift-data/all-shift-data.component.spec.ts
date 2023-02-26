import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllShiftDataComponent } from './all-shift-data.component';

describe('AllShiftDataComponent', () => {
  let component: AllShiftDataComponent;
  let fixture: ComponentFixture<AllShiftDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllShiftDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllShiftDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
