package com.attendance.app.repository;


import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.app.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);
    Employee findByMobile(String mobile);
//    Optional<User> findByEmail(String email);
}
