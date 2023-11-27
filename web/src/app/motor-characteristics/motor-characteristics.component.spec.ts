import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MotorCharacteristicsComponent } from './motor-characteristics.component';

describe('MotorCharacteristicsComponent', () => {
  let component: MotorCharacteristicsComponent;
  let fixture: ComponentFixture<MotorCharacteristicsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MotorCharacteristicsComponent]
    });
    fixture = TestBed.createComponent(MotorCharacteristicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
