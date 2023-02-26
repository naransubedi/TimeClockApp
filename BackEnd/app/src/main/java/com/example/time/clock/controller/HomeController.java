package com.example.time.clock.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.time.clock.service.TimeClockService;

@RestController
public class HomeController {

    @Autowired TimeClockService timeClockService;

    @GetMapping("/employees/{employee-id}/shifts")
    public ResponseEntity<?> getShiftForEmployee(@PathVariable("employee-id") Long employeeId){
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @PostMapping("/{shift-id}/start")
    public ResponseEntity<?> startShift(@PathVariable("shift-id") Long shiftId, @RequestParam("start-date") @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") Date startDate){
        timeClockService.startShift(shiftId, startDate);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @PostMapping("/{shift-id}/end")
    public ResponseEntity<?> endShift(@PathVariable("shift-id") Long shiftId, @RequestParam("end-date") @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") Date endDate){
        timeClockService.endShift(shiftId, endDate);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @PostMapping("/{shift-id}/break/start")
    public ResponseEntity<?> startBreakForShift(@PathVariable("shift-id") Long shiftId, @RequestParam("start-date") @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") Date startDate){
        timeClockService.startBreak(shiftId, startDate);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @PostMapping("/{shift-id}/break/end")
    public ResponseEntity<?> endBreakForShift(@PathVariable("shift-id") Long shiftId, @RequestParam("end-date") @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") Date endDate){
        timeClockService.endBreak(shiftId, endDate);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @PostMapping("/{shift-id}/lunch/start")
    public ResponseEntity<?> startLunchForShift(@PathVariable("shift-id") Long shiftId, @RequestParam("start-date") @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") Date startDate){
        timeClockService.startLunch(shiftId, startDate);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @PostMapping("/{shift-id}/lunch/end")
    public ResponseEntity<?> endLunchForShift(@PathVariable("shift-id") Long shiftId, @RequestParam("end-date") @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") Date endDate){
        timeClockService.endLunch(shiftId, endDate);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @PostMapping("/{employee-id}/assign/shift")
    public ResponseEntity<?> assignShiftForEmployee(@PathVariable("employee-id") Long employeeId) {
        timeClockService.assignShiftForEmployee(employeeId);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
    
}
