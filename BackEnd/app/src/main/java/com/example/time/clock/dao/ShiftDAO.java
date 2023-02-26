package com.example.time.clock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.time.clock.entity.Employee;
import com.example.time.clock.entity.Shift;

@Repository
public interface ShiftDAO extends JpaRepository<Shift, Long> {
    List<Shift> findByEmployee(Employee employee);
}
