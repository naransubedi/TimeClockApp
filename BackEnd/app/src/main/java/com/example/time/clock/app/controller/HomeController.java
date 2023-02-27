package com.example.time.clock.app.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.time.clock.app.model.ShiftDTO;
import com.example.time.clock.app.service.TimeClockService;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @Autowired TimeClockService timeClockService;

    @GetMapping(value = "/employees/{employee-id}/shifts")
    public ResponseEntity<?> getShiftForEmployee(@PathVariable("employee-id") Long employeeId){
        ResponseEntity<?> response ;
        try {
            List<ShiftDTO> shifts = timeClockService.getAllShiftForEmployee(employeeId);
            response = new ResponseEntity<List<ShiftDTO>>(shifts, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/{shift-id}/start")
    public ResponseEntity<?> startEndShift(@PathVariable("shift-id") Long shiftId, 
        @RequestParam(name = "start-date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") Date startDate,
        @RequestParam(name = "end-date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") Date endDate){
            ResponseEntity<?> response ;
            try {
                timeClockService.startEndShift(shiftId, startDate, endDate);
                response = new ResponseEntity<String>("success", HttpStatus.OK);
            } catch (Exception e) {
                response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return response;
    }

    @PostMapping("/{shift-id}/break/start")
    public ResponseEntity<?> startEndBreakForShift(@PathVariable("shift-id") Long shiftId, 
        @RequestParam("start-date") @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") Date startDate,
        @RequestParam("end-date") @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") Date endDate){
        ResponseEntity<?> response ;
        try {
            timeClockService.startEndBreak(shiftId, startDate, endDate);
            response = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/{shift-id}/lunch/start")
    public ResponseEntity<?> startEndLunchForShift(@PathVariable("shift-id") Long shiftId, 
        @RequestParam("start-date") @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") Date startDate,
        @RequestParam("end-date") @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") Date endDate){
        
        ResponseEntity<?> response ;
        try {
            timeClockService.startEndLunch(shiftId, startDate, endDate);
            response = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;        
    }

    @PostMapping("/{employee-id}/assign/shift")
    public ResponseEntity<?> assignShiftForEmployee(@PathVariable("employee-id") Long employeeId,
    @RequestParam("from-date") String fromDate,
    @RequestParam("to-date") String toDate
    ) throws ParseException {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        timeClockService.assignShiftForEmployee(employeeId, formatter.parse(fromDate), formatter.parse(toDate));
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}
