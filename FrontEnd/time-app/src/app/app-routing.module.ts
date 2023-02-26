import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllShiftDataComponent } from './all-shift-data/all-shift-data.component';
import { DashoardComponent } from './dashoard/dashoard.component';
import { ShiftComponent } from './shift/shift.component';

const routes: Routes = [
  {
    path: '',
    component: DashoardComponent

  },
  {
    path: 'shift',
    component: ShiftComponent
  },
  {
    path: 'all-shift-data',
    component: AllShiftDataComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
