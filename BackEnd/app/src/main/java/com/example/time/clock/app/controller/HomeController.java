package com.example.time.clock.app.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.time.clock.app.model.DateDTO;
import com.example.time.clock.app.model.ShiftDTO;
import com.example.time.clock.app.service.TimeClockService;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @Autowired TimeClockService timeClockService;

    @GetMapping(value = "/employees/{employee-id}/shifts")
    public ResponseEntity<?> getAllShiftForEmployee(@PathVariable("employee-id") String employeeId){
        ResponseEntity<?> response ;
        try {
            List<ShiftDTO> shifts = timeClockService.getAllShiftForEmployee(Long.parseLong(employeeId));
            response = new ResponseEntity<List<ShiftDTO>>(shifts, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping(value = "/employees/{employee-id}/shifts/today")
    public ResponseEntity<?> getTodaysShiftForEmployee(@PathVariable("employee-id") String employeeId){
        ResponseEntity<?> response ;
        try {
            List<ShiftDTO> shifts = timeClockService.getTodaysShiftForEmployee(Long.parseLong(employeeId));
            response = new ResponseEntity<List<ShiftDTO>>(shifts, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/{shift-id}/start-end")
    public ResponseEntity<?> startEndShift(@PathVariable("shift-id") String shiftId, @RequestBody DateDTO dateDTO){
            ResponseEntity<?> response ;
            try {
                timeClockService.startEndShift(Long.parseLong(shiftId), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateDTO.getStartDate()), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateDTO.getEndDate()));
                response = new ResponseEntity<String>("success", HttpStatus.OK);
            } catch (Exception e) {
                response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return response;
    }

    @PostMapping("/{shift-id}/break/start-end")
    public ResponseEntity<?> startEndBreakForShift(@PathVariable("shift-id") String shiftId,
    @RequestBody DateDTO dateDTO){
        ResponseEntity<?> response ;
        try {
            timeClockService.startEndBreak(Long.parseLong(shiftId), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateDTO.getStartDate()), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateDTO.getEndDate()));
            response = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/{shift-id}/lunch/start-end")
    public ResponseEntity<?> startEndLunchForShift(@PathVariable("shift-id") String shiftId, 
    @RequestBody DateDTO dateDTO){
        
        ResponseEntity<?> response ;
        try {
            timeClockService.startEndLunch(Long.parseLong(shiftId), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateDTO.getStartDate()), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateDTO.getEndDate()));
            response = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;        
    }

    @PostMapping("/{employee-id}/assign/shift-end")
    public ResponseEntity<?> assignShiftForEmployee(@PathVariable("employee-id") String employeeId,
    @RequestBody DateDTO dateDTO
    ) throws ParseException {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        timeClockService.assignShiftForEmployee(Long.parseLong(employeeId), formatter.parse(dateDTO.getStartDate()), formatter.parse(dateDTO.getEndDate()));
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}
