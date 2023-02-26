package com.example.time.clock.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.time.clock.dao.BreakDAO;
import com.example.time.clock.dao.EmployeeDAO;
import com.example.time.clock.dao.LunchDAO;
import com.example.time.clock.dao.ShiftDAO;
import com.example.time.clock.entity.Break;
import com.example.time.clock.entity.Lunch;
import com.example.time.clock.entity.Shift;

@Service
public class TimeClockService {

    @Autowired ShiftDAO shiftDAO;
    @Autowired EmployeeDAO employeeDAO;
    @Autowired BreakDAO breakDAO;
    @Autowired LunchDAO lunchDAO;

    public List<Shift> getAllShiftForEmployee(Long employeeId){
        
        return shiftDAO.findByEmployee(employeeDAO.findById(employeeId).get());
        
    }

    public void startShift(Long shiftId, Date startDate){

        Shift shift = shiftDAO.findById(shiftId).get();
        shift.setStarDate(startDate);
        shiftDAO.save(shift);

    }

    public void endShift(Long shiftId, Date endDate){
        Shift shift = shiftDAO.findById(shiftId).get();
        shift.setEndDate(endDate);
        shiftDAO.save(shift);
    }

    public void startBreak(Long shiftId, Date startDate){
        Shift shift = shiftDAO.findById(shiftId).get();
        Break shiftBreak = new Break();
        shiftBreak.setStarDate(startDate);
        shiftBreak.setShift(shift);
        breakDAO.save(shiftBreak);
    }

    public void endBreak(Long shiftId, Date endDate){
        Shift shift = shiftDAO.findById(shiftId).get();
        Break shiftBreak = new Break();
        shiftBreak.setEndDate(endDate);
        shiftBreak.setShift(shift);
        breakDAO.save(shiftBreak);
    }

    public void startLunch(Long shiftId, Date startDate){
        Shift shift = shiftDAO.findById(shiftId).get();
        Lunch lunch = new Lunch();
        lunch.setStarDate(startDate);
        lunch.setShift(shift);
        lunchDAO.save(lunch);
    }

    public void endLunch(Long shiftId, Date endDate){
        Shift shift = shiftDAO.findById(shiftId).get();
        Lunch lunch = new Lunch();
        lunch.setEndDate(endDate);
        lunch.setShift(shift);
        lunchDAO.save(lunch);
    }

    public void assignShiftForEmployee(Long employeeId){
        Shift shift = new Shift();
        shift.setStatus("Active");
        shift.setEmployee(employeeDAO.findById(employeeId).get());
        shiftDAO.save(shift);
    }
}
