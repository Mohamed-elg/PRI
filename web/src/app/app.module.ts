import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MotorCharacteristicsComponent } from './motor-characteristics/motor-characteristics.component';
import { EquipmentCharacteristicsComponent } from './equipment-characteristics/equipment-characteristics.component';
import { TasksComponent } from './tasks/tasks.component';
import { MechanicalSheetComponent } from './mechanical-sheet/mechanical-sheet.component';
import { DimensionalInspectionComponent } from './dimensional-inspection/dimensional-inspection.component';
import { QuotationComponent } from './quotation/quotation.component';
import { ConnectionComponent } from './connection/connection.component';
<<<<<<< HEAD
import { ClientComponent } from './client/client.component';
import { MotorComponent } from './motor/motor.component';
import { OtherEquipementComponent } from './other-equipement/other-equipement.component';
import { DiagnosticComponent } from './diagnostic/diagnostic.component';
=======
import { ListMechanicalSheetsComponent } from './list-mechanical-sheets/list-mechanical-sheets.component';
>>>>>>> 18919bdc5377d6aefdfb1ae72c413ebb4a661d6d

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
<<<<<<< HEAD
    ClientComponent,
    MotorComponent,
    OtherEquipementComponent,
    DiagnosticComponent
=======
    ListMechanicalSheetsComponent
>>>>>>> 18919bdc5377d6aefdfb1ae72c413ebb4a661d6d
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
