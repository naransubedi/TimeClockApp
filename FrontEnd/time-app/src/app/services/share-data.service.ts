import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShareDataService {
  private employeeId = new BehaviorSubject(null);
  empId = this.employeeId.asObservable();
  constructor() { }

  updateEmployeeId(empId: number) {
    this.employeeId.next(empId)
  }
}
