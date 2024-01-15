import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MotorCharacteristicsComponent } from './motor-characteristics/motor-characteristics.component';
import { EquipmentCharacteristicsComponent } from './equipment-characteristics/equipment-characteristics.component';
import { TasksComponent } from './tasks/tasks.component';
import { MechanicalSheetComponent } from './mechanical-sheet/mechanical-sheet.component';
import { DimensionalInspectionComponent } from './dimensional-inspection/dimensional-inspection.component';
import { QuotationComponent } from './quotation/quotation.component';
import { ConnectionComponent } from './connection/connection.component';
import { ClientComponent } from './client/client.component';
import { MotorComponent } from './motor/motor.component';
import { OtherEquipementComponent } from './other-equipement/other-equipement.component';
import { DiagnosticComponent } from './diagnostic/diagnostic.component';
import { ListMechanicalSheetsComponent } from './list-mechanical-sheets/list-mechanical-sheets.component';

@NgModule({
  declarations: [
    AppComponent,
    MotorCharacteristicsComponent,
    EquipmentCharacteristicsComponent,
    TasksComponent,
    MechanicalSheetComponent,
    DimensionalInspectionComponent,
    QuotationComponent,
    ConnectionComponent,
    ClientComponent,
    MotorComponent,
    OtherEquipementComponent,
    DiagnosticComponent,
    ListMechanicalSheetsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
