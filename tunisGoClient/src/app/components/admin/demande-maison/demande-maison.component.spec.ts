import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandeMaisonComponent } from './demande-maison.component';

describe('DemandeMaisonComponent', () => {
  let component: DemandeMaisonComponent;
  let fixture: ComponentFixture<DemandeMaisonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DemandeMaisonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DemandeMaisonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
