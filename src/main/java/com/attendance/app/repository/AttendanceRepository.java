package com.attendance.app.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.app.model.Attendance;


public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
