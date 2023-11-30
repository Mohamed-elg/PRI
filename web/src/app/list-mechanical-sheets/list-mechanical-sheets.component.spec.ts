import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListMechanicalSheetsComponent } from './list-mechanical-sheets.component';

describe('ListMechanicalSheetsComponent', () => {
  let component: ListMechanicalSheetsComponent;
  let fixture: ComponentFixture<ListMechanicalSheetsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListMechanicalSheetsComponent]
    });
    fixture = TestBed.createComponent(ListMechanicalSheetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
