import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListMaisonsComponent } from './list-maisons.component';

describe('ListMaisonsComponent', () => {
  let component: ListMaisonsComponent;
  let fixture: ComponentFixture<ListMaisonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListMaisonsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListMaisonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
