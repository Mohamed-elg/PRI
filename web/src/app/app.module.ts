import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MotorCharacteristicsComponent } from './motor-characteristics/motor-characteristics.component';
import { EquipmentCharacteristicsComponent } from './equipment-characteristics/equipment-characteristics.component';
import { TasksComponent } from './tasks/tasks.component';
import { MechanicalSheetComponent } from './mechanical-sheet/mechanical-sheet.component';

@NgModule({
  declarations: [
    AppComponent,
    MotorCharacteristicsComponent,
    EquipmentCharacteristicsComponent,
    TasksComponent,
    MechanicalSheetComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
