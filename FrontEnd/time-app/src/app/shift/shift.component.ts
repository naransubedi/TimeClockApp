import { Component, ElementRef, Input, OnInit, QueryList, Renderer2, ViewChild, ViewChildren } from '@angular/core';
import { Router } from '@angular/router';
import { Shift } from '../model/shift.model';
import { ShareDataService } from '../services/share-data.service';
import { TimeClockService } from '../services/time-clock.service';

@Component({
  selector: 'app-shift',
  templateUrl: './shift.component.html',
  styleUrls: ['./shift.component.css']
})
export class ShiftComponent implements OnInit {

  @ViewChildren("startEndShiftButton") startEndShiftButtons: QueryList<ElementRef>;

  @ViewChild("startEndBreakButton") startEndBreaktButton: ElementRef;

  @ViewChild("startEndLunchButton") startEndLunchButton: ElementRef;

  // @ViewChildren("slides") private slides: QueryList<ElementRef>;


  isShiftStarted: boolean;
  isBreakStarted: boolean;
  isLunchStarted: boolean;
  isShiftEnded: boolean;

  employeeId: number;
  shifts: Shift[];
  checkIndex: number;

  constructor(private renderer : Renderer2, private router: Router, private timeClockService:TimeClockService, private shareDataService:ShareDataService) { }

  ngOnInit(): void {
    this.isShiftStarted = false;
    this.isShiftEnded = false;
    this.shareDataService.empId.subscribe(employeeId => this.employeeId = employeeId);
    this.shifts = this.timeClockService.getAllShiftForEmployeeId(this.employeeId);
  }

  startShift(shiftId:number, indexNum:number){
    this.checkIndex = indexNum;
    const element = this.startEndShiftButtons.filter((element, index) => index === indexNum);
    var shiftStartButtonText = 'Start Shift';
    var shiftEndButtonText = 'End Shift';
    if(element[0].nativeElement.innerHTML === shiftStartButtonText){
      this.renderer.setProperty(
        element[0].nativeElement,
        "innerHTML",
        shiftEndButtonText
      );
      this.isShiftStarted = true;
      this.timeClockService.shiftStartEnd(shiftId, shiftStartButtonText);
    }else if(element[0].nativeElement.innerHTML === shiftEndButtonText) {
      this.renderer.setProperty(
        element[0].nativeElement,
        "innerHTML",
        shiftStartButtonText
      );
      this.isShiftStarted = false;
      this.isShiftEnded = true;
      this.timeClockService.shiftStartEnd(shiftId, shiftEndButtonText);
    }
  }

  startBreak(shiftId:number, indexNum:number){
    if(this.startEndBreaktButton.nativeElement.innerHTML === 'Start Your Break'){
      this.renderer.setProperty(
        this.startEndBreaktButton.nativeElement,
        "innerHTML",
        "End Your Break"
      );
      this.isBreakStarted = true;
      this.timeClockService.breakStartEnd(shiftId, 'start')
    }else {
      this.renderer.setProperty(
        this.startEndBreaktButton.nativeElement,
        "innerHTML",
        "Start Your Break"
      );
      this.isBreakStarted = false;
      this.timeClockService.breakStartEnd(shiftId, 'end')
    }
  }

  startLunch(shiftId:number, indexNum:number){
    if(this.startEndLunchButton.nativeElement.innerHTML === 'Start Your Lunch'){
      this.renderer.setProperty(
        this.startEndLunchButton.nativeElement,
        "innerHTML",
        "End Your Lunch"
      );
      
    this.isLunchStarted = true;
    this.timeClockService.lunchStartEnd(shiftId, 'start');
    }else {
      this.renderer.setProperty(
        this.startEndLunchButton.nativeElement,
        "innerHTML",
        "Start Your Lunch"
      );
      
    this.isLunchStarted = false;
    this.timeClockService.lunchStartEnd(shiftId, 'end');
    }
  }

  viewAllShiftData(){
    this.router.navigate(['all-shift-data']);
  }
}