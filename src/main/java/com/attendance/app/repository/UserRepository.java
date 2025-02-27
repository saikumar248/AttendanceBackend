package com.attendance.app.repository;


import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.app.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByMobile(String mobile);
//    Optional findById(Long Id);
//    Optional<User> findByEmail(String email);
}
