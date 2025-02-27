package com.attendance.app.repository;


import java.util.List;
import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.app.model.Interns;


public interface InternRepository extends JpaRepository<Interns, Long> {
    Interns findByEmail(String email);
    Interns findByMobile(String mobile);
    List<Interns> findByDepartment(String department);

//    Optional<User> findByEmail(String email);
}
