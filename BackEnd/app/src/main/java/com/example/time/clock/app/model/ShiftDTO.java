package com.example.time.clock.app.model;

import java.util.List;

public class ShiftDTO {
    private String shiftId;
    private String employeeId;
    private String starDate;
    private String endDate;
    private String fromDate;
    private String toDate;
    private String status;
    private List<BreakDTO> breaks;
    private LunchDTO lunch;

    public List<BreakDTO> getBreaks() {
        return breaks;
    }
    public void setBreaks(List<BreakDTO> breaks) {
        this.breaks = breaks;
    }
    public LunchDTO getLunch() {
        return lunch;
    }
    public void setLunch(LunchDTO lunch) {
        this.lunch = lunch;
    }
    public String getShiftId() {
        return shiftId;
    }
    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getStarDate() {
        return starDate;
    }
    public void setStarDate(String starDate) {
        this.starDate = starDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getFromDate() {
        return fromDate;
    }
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
    public String getToDate() {
        return toDate;
    }
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    } 
}
