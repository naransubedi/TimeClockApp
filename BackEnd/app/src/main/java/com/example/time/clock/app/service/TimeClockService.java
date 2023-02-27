package com.example.time.clock.app.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.time.clock.app.dao.BreakDAO;
import com.example.time.clock.app.dao.EmployeeDAO;
import com.example.time.clock.app.dao.LunchDAO;
import com.example.time.clock.app.dao.ShiftDAO;
import com.example.time.clock.app.entity.Break;
import com.example.time.clock.app.entity.Lunch;
import com.example.time.clock.app.entity.Shift;
import com.example.time.clock.app.model.ShiftDTO;

@Service
public class TimeClockService {

    @Autowired ShiftDAO shiftDAO;
    @Autowired EmployeeDAO employeeDAO;
    @Autowired BreakDAO breakDAO;
    @Autowired LunchDAO lunchDAO;

    public List<ShiftDTO> getAllShiftForEmployee(Long employeeId) throws ParseException{
        List<ShiftDTO> shiftDtos = new ArrayList<>();

        List<Shift> shifts = shiftDAO.findByEmployee(employeeDAO.findById(employeeId).get());

        return shiftDtos;

    }


    public List<ShiftDTO> getTodaysShiftForEmployee(Long employeeId) throws ParseException{

        List<ShiftDTO> shiftDtos = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date currentDate = new Date();

        Date crrentStartDate = df.parse(df.format(currentDate));
        Date currentEndDate = df.parse(new SimpleDateFormat("yyyy-MM-dd").format(currentDate) + " 23:59:59");

        List<Shift> shifts = shiftDAO.findByEmployee(employeeDAO.findById(employeeId).get())
                .stream()
                .filter(shift -> shift.getFromDate().after(crrentStartDate) && shift.getFromDate().before(currentEndDate))
                .collect(Collectors.toList());

        for(Shift shift : shifts){
            ShiftDTO shiftDto = new ShiftDTO();
            shiftDto.setEmployeeId(String.valueOf(employeeId));
            shiftDto.setShiftId(String.valueOf(shift.getId()));
            shiftDto.setStatus(shift.getStatus());
            shiftDto.setFromDate(df.format(shift.getFromDate()));
            shiftDto.setToDate(df.format(shift.getToDate()));

            shiftDtos.add(shiftDto);
        }

        shiftDtos.stream().max(Comparator.comparing(ShiftDTO::getFromDate)).get().setStatus("Active");
        return shiftDtos;
        
    }

    public void startEndShift(Long shiftId, Date startDate, Date endDate){
        Shift shift = shiftDAO.findById(shiftId).get();
        if(startDate == null){
            shift.setEndDate(endDate);
            shift.setStatus("Not-Active");
            shiftDAO.save(shift);    
        } else if(endDate == null){
            shift.setEndDate(endDate);
            shiftDAO.save(shift);    
        }
    }

    public void startEndBreak(Long shiftId, Date startDate, Date endDate){
        Shift shift = shiftDAO.findById(shiftId).get();
        if(startDate == null){
            Break shiftBreak = new Break();
            shiftBreak.setStarDate(startDate);
            shiftBreak.setShift(shift);
            breakDAO.save(shiftBreak);
        }else if(endDate == null){
            Break shiftBreak = shift.getBreaks().stream().filter(b -> b.getEndDate() == null).findFirst().get();
            shiftBreak.setEndDate(endDate);
            shiftBreak.setShift(shift);
            breakDAO.save(shiftBreak);
        }
    }

    public void startEndLunch(Long shiftId, Date startDate, Date endDate){
        Shift shift = shiftDAO.findById(shiftId).get();
        if(startDate == null){
            Lunch lunch = new Lunch();
            lunch.setStarDate(startDate);
            lunch.setShift(shift);
            lunchDAO.save(lunch);
        }else if(endDate == null){
            Lunch lunch = shift.getLunch();
            lunch.setEndDate(endDate);
            lunch.setShift(shift);
            lunchDAO.save(lunch);
        }
    }

    public void assignShiftForEmployee(Long employeeId, Date fromDate, Date toDate){
        Shift shift = new Shift();
        shift.setStatus("Active");
        shift.setFromDate(fromDate);
        shift.setToDate(toDate);
        shift.setStatus("Not-Active");
        shift.setEmployee(employeeDAO.findById(employeeId).get());
        shiftDAO.save(shift); 
    }
}
