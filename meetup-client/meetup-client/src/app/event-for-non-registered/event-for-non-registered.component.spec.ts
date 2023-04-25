import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventForNonRegisteredComponent } from './event-for-non-registered.component';

describe('EventForNonRegisteredComponent', () => {
  let component: EventForNonRegisteredComponent;
  let fixture: ComponentFixture<EventForNonRegisteredComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventForNonRegisteredComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventForNonRegisteredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
