import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Shift } from '../model/shift.model';

@Injectable({
  providedIn: 'root'
})
export class TimeClockService {

  constructor(private http:HttpClient) { }

  getAllShiftForEmployeeId(employeeId:number){
    const shifts:Shift[] = [
      {
        id: 1,
        status: 'Active',
        startDate: null,
        endDate: null,
        lunch: {
          id: null,
          startDate: null,
          endDate: null
        },
        break: {
          id: null,
          startDate: null,
          endDate: null
        }
      },
      {
        id: 2,
        status: 'Not-Active',
        startDate: null,
        endDate: null,
        lunch: {
          id: null,
          startDate: null,
          endDate: null
        },
        break: {
          id: null,
          startDate: null,
          endDate: null
        }
      },
      {
        id: 3,
        status: 'Not-Active',
        startDate: null,
        endDate: null,
        lunch: {
          id: null,
          startDate: null,
          endDate: null
        },
        break: {
          id: null,
          startDate: null,
          endDate: null
        }
      }

    ]
    return shifts;
    // this.http.get()
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
