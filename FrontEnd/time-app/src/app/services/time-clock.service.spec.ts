import { TestBed } from '@angular/core/testing';

import { TimeClockService } from './time-clock.service';

describe('TimeClockService', () => {
  let service: TimeClockService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TimeClockService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
