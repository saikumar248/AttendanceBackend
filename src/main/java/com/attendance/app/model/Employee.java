package com.attendance.app.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;

//    @Column(nullable = false)
//    private String batchNo;
    
    @Column(nullable = false)
    private String designation;
    
    
    @Column(nullable = false, unique = true, length = 10)
    private String mobile;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = true)
    private String department;
    
    private String imageName;
    private String imageType;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] profilePic;
    
    @Column(nullable = true)
    private String checkInStatus = "Yet to CheckIn"; // Default check-in status
    
}