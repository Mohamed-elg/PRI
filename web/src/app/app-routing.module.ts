import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MechanicalSheetComponent } from './mechanical-sheet/mechanical-sheet.component';
import { ConnectionComponent } from './connection/connection.component';
import { ListMechanicalSheetsComponent } from './list-mechanical-sheets/list-mechanical-sheets.component';
import { AddMechanicalSheetComponent } from './add-mechanical-sheet/add-mechanical-sheet.component';

const routes: Routes = [
  { path: 'add-mechanical-sheet', component: AddMechanicalSheetComponent },
  { path: 'connection', component: ConnectionComponent },
  { path: 'list-mechanical-sheets', component: ListMechanicalSheetsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
