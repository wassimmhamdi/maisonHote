import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandeActivityComponent } from './demande-activity.component';

describe('DemandeActivityComponent', () => {
  let component: DemandeActivityComponent;
  let fixture: ComponentFixture<DemandeActivityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DemandeActivityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DemandeActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
