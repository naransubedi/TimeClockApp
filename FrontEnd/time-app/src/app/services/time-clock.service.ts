import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Shift } from '../model/shift.model';

@Injectable({
  providedIn: 'root'
})
export class TimeClockService {

  constructor(private http:HttpClient) { }

  getAllShiftForEmployeeId(employeeId:number){
    const headers = new HttpHeaders({
      'content-type': 'application/json',
      'accept': 'application/json'
    })
    var response = this.http.get<Shift>('http://localhost:8080/api/v1/employees/'+employeeId + '/shifts');
    return response;
  }

  shiftStartEnd(shiftId: number, startOrEnd:string){

  }

  breakStartEnd(shiftId: number, startOrEnd:string){

  }

  lunchStartEnd(shiftId: number, startOrEnd:string){

  }

  getAllShiftData(employeeId:number){

  }
}
