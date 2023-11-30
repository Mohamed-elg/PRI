import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OtherEquipementComponent } from './other-equipement.component';

describe('OtherEquipementComponent', () => {
  let component: OtherEquipementComponent;
  let fixture: ComponentFixture<OtherEquipementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OtherEquipementComponent]
    });
    fixture = TestBed.createComponent(OtherEquipementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
