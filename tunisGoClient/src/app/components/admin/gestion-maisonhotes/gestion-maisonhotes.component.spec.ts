import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionMaisonhotesComponent } from './gestion-maisonhotes.component';

describe('GestionMaisonhotesComponent', () => {
  let component: GestionMaisonhotesComponent;
  let fixture: ComponentFixture<GestionMaisonhotesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionMaisonhotesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionMaisonhotesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
