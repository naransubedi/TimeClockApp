import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DashoardComponent } from './dashoard/dashoard.component';
import { ShiftComponent } from './shift/shift.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AllShiftDataComponent } from './all-shift-data/all-shift-data.component';
import { HttpClientModule} from '@angular/common/http';
import { StartShiftComponent } from './shift/start-shift/start-shift.component';
import { DatePipe } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    DashoardComponent,
    ShiftComponent,
    AllShiftDataComponent,
    StartShiftComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
