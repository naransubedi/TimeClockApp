import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Shift } from '../model/shift.model';

@Injectable({
  providedIn: 'root'
})
export class TimeClockService {

  constructor(private http:HttpClient, private datePipe: DatePipe) { }

  getAllShiftForEmployeeId(employeeId:number){
    const headers = new HttpHeaders({
      'content-type': 'application/json',
      'accept': 'application/json'
    })
    var response = this.http.get<Shift>('http://localhost:8080/api/v1/employees/'+employeeId + '/shifts/today');
    return response;
  }

  shiftStartEnd(shiftId: number, startOrEnd:string){
    const currentDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd hh:mm:ss');
    var postBody;
    if(startOrEnd === 'start'){
      postBody = {
        startDate: currentDate,
        endDate: null
      }
    } else if (startOrEnd === 'end'){
      postBody = {
        startDate: null,
        endDate: currentDate
      }
    }

    var response = this.http.post('http://localhost:8080/api/v1/shifts/' + shiftId + '/start-end' , postBody);
    return response;
  }

  breakStartEnd(shiftId: number, startOrEnd:string){
    const currentDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd hh:mm:ss');
    var postBody;
    if(startOrEnd === 'start'){
      postBody = {
        startDate: currentDate,
        endDate: null
      }
    } else if (startOrEnd === 'end'){
      postBody = {
        startDate: null,
        endDate: currentDate
      }
    }

    var response = this.http.post('http://localhost:8080/api/v1/shifts/' + shiftId + '/break/start-end' , postBody);
    return response;
  }

  lunchStartEnd(shiftId: number, startOrEnd:string){
    const currentDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd hh:mm:ss');
    var postBody;
    if(startOrEnd === 'start'){
      postBody = {
        startDate: currentDate,
        endDate: null
      }
    } else if (startOrEnd === 'end'){
      postBody = {
        startDate: null,
        endDate: currentDate
      }
    }

    var response = this.http.post('http://localhost:8080/api/v1/shifts/' + shiftId + '/lunch/start-end' , postBody);
    return response;
  }

  getAllShiftData(employeeId:number){
    return this.http.get<Shift>('http://localhost:8080/api/v1/employees/'+employeeId + '/shifts');
  }
}
