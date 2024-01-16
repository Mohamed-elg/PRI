import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MechanicalSheetComponent } from './mechanical-sheet/mechanical-sheet.component';
import { ConnectionComponent } from './connection/connection.component';
import { ListMechanicalSheetsComponent } from './list-mechanical-sheets/list-mechanical-sheets.component';
import { AddMechanicalSheetComponent } from './add-mechanical-sheet/add-mechanical-sheet.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path: 'add-mechanical-sheet', component: AddMechanicalSheetComponent },
  { path: 'connection', component: ConnectionComponent },
  { path: 'list-mechanical-sheets', component: ListMechanicalSheetsComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: '/connection', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
