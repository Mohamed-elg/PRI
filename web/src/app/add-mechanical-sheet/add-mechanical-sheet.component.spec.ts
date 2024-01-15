import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMechanicalSheetComponent } from './add-mechanical-sheet.component';

describe('AddMechanicalSheetComponent', () => {
  let component: AddMechanicalSheetComponent;
  let fixture: ComponentFixture<AddMechanicalSheetComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddMechanicalSheetComponent]
    });
    fixture = TestBed.createComponent(AddMechanicalSheetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
