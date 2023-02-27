package com.example.time.clock.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.time.clock.app.entity.Break;

@Repository
public interface BreakDAO extends JpaRepository<Break, Long> {
    
}
