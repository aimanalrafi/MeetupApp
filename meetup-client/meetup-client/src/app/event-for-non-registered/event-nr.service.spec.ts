import { TestBed } from '@angular/core/testing';

import { EventNRService } from './event-nr.service';

describe('EventNRService', () => {
  let service: EventNRService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EventNRService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
