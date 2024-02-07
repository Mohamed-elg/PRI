import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MechanicalSheetComponent } from './mechanical-sheet/mechanical-sheet.component';
import { ConnectionComponent } from './connection/connection.component';
import { ListMechanicalSheetsComponent } from './list-mechanical-sheets/list-mechanical-sheets.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path: 'connection', component: ConnectionComponent },
  { path: 'list-mechanical-sheets', component: ListMechanicalSheetsComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'mechanical-sheet/:action/:id', component: MechanicalSheetComponent },
  { path: '', redirectTo: '/connection', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
