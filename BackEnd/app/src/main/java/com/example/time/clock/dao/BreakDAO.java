package com.example.time.clock.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.time.clock.entity.Break;

@Repository
public interface BreakDAO extends JpaRepository<Break, Long> {
    
}
