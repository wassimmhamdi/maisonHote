import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TinderInterfaceComponent } from './tinder-interface.component';

describe('TinderInterfaceComponent', () => {
  let component: TinderInterfaceComponent;
  let fixture: ComponentFixture<TinderInterfaceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TinderInterfaceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TinderInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
