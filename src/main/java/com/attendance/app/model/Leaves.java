package com.attendance.app.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Leaves {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String department;
    
//    @Column(nullable = false, unique = true)
    private Date startDate;
    
//    @Column(nullable = false, unique = true)
    private Date endDate;
    
    private String leaveStatus;
    
    private String reason;
    
    private Long userId;
    private String fullName;
    private String batchNo;
    

    
}