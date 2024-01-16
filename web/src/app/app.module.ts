import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MechanicalSheetComponent } from './mechanical-sheet/mechanical-sheet.component';
import { QuotationComponent } from './quotation/quotation.component';
import { ConnectionComponent } from './connection/connection.component';
import { ClientComponent } from './client/client.component';
import { EquipementComponent } from './equipment/equipment.component';
import { OtherEquipementComponent } from './other-equipement/other-equipement.component';
import { ListMechanicalSheetsComponent } from './list-mechanical-sheets/list-mechanical-sheets.component';
import { AddMechanicalSheetComponent } from './add-mechanical-sheet/add-mechanical-sheet.component';

@NgModule({
  declarations: [
    AppComponent,
    MechanicalSheetComponent,
    QuotationComponent,
    ConnectionComponent,
    ClientComponent,
    EquipementComponent,
    OtherEquipementComponent,
    ListMechanicalSheetsComponent,
    AddMechanicalSheetComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
