import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MechanicalSheetComponent } from './mechanical-sheet.component';

describe('MechanicalSheetComponent', () => {
  let component: MechanicalSheetComponent;
  let fixture: ComponentFixture<MechanicalSheetComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MechanicalSheetComponent]
    });
    fixture = TestBed.createComponent(MechanicalSheetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
