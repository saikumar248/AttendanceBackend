package com.attendance.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.app.model.Leaves;


public interface LeaveRepository extends JpaRepository<Leaves, Long> {

}
