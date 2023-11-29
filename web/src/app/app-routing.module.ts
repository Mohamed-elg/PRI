import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MechanicalSheetComponent } from './mechanical-sheet/mechanical-sheet.component';

const routes: Routes = [
  { path: 'mechanical-sheet', component: MechanicalSheetComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
