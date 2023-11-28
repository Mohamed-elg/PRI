import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DimensionalInspectionComponent } from './dimensional-inspection.component';

describe('DimensionalInspectionComponent', () => {
  let component: DimensionalInspectionComponent;
  let fixture: ComponentFixture<DimensionalInspectionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DimensionalInspectionComponent]
    });
    fixture = TestBed.createComponent(DimensionalInspectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
