import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipmentCharacteristicsComponent } from './equipment-characteristics.component';

describe('EquipmentCharacteristicsComponent', () => {
  let component: EquipmentCharacteristicsComponent;
  let fixture: ComponentFixture<EquipmentCharacteristicsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EquipmentCharacteristicsComponent]
    });
    fixture = TestBed.createComponent(EquipmentCharacteristicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
