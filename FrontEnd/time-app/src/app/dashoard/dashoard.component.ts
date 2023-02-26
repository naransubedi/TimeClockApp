import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ShareDataService } from '../services/share-data.service';

@Component({
  selector: 'app-dashoard',
  templateUrl: './dashoard.component.html',
  styleUrls: ['./dashoard.component.css']
})
export class DashoardComponent implements OnInit {

  employeeCheckForm : FormGroup;

  constructor(private router: Router, private shareDataService: ShareDataService) { }

  ngOnInit(): void {
    this.employeeCheckForm = new FormGroup({
      'employeeId' : new FormControl()
    })
  }

  checkEmployeeWithId(){
    var employeeId = this.employeeCheckForm.value.employeeId;
    this.shareDataService.updateEmployeeId(employeeId);
    this.router.navigate(['shift']);
  }

}
