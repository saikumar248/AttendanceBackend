package com.attendance.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.attendance.app.model.Attendance;
import com.attendance.app.model.Employee;
import com.attendance.app.model.Interns;
import com.attendance.app.model.Leaves;
import com.attendance.app.model.User;
import com.attendance.app.repository.InternRepository;
import com.attendance.app.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    private UserService userService;
    
	@Autowired
	private InternRepository internRepository;

    @GetMapping("/fetchall")
    public ResponseEntity<List<User>> fetchAll() {
        return userService.fetchAll();  
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestPart User user, @RequestPart MultipartFile imageFile) {
    	System.out.println("Function is going to execute");
        try {
            User product1 = userService.addUser(user, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
//    @PostMapping(value ="/addIntern",  consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//    public ResponseEntity<?> addIntern(@RequestBody Interns intern, @RequestPart MultipartFile imageFile) throws IOException {
//        Interns newIntern = userService.addIntern(intern,imageFile);
//        return ResponseEntity.ok("User registered successfully!");
//    }
    @PostMapping(value = "/addIntern", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> addIntern(
            @RequestPart("intern") String userJson, 
            @RequestPart("imageFile") MultipartFile imageFile) throws IOException {
    	
        ObjectMapper objectMapper = new ObjectMapper();
        Interns intern = objectMapper.readValue(userJson, Interns.class);
        Interns newIntern = userService.addIntern(intern, imageFile);
        return ResponseEntity.ok("User registered successfully!");
    }
    
    
    @PostMapping(value = "/addEmployee", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> addEmployee(
            @RequestPart("intern") String userJson, 
            @RequestPart("imageFile") MultipartFile imageFile) throws IOException {
    	
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue(userJson, Employee.class);
        Employee newEmployee = userService.addEmployee(employee, imageFile);
        return ResponseEntity.ok("User registered successfully!");
    }
    
    @GetMapping("/getInterns/{department}") 
    public ResponseEntity<List<Interns>> getInternsByDepartment(@PathVariable String department) {
        List<Interns> interns = internRepository.findByDepartment(department);
        return ResponseEntity.ok(interns);
    }
    
    @PostMapping("/saveLeave")
    public ResponseEntity<Leaves> saveLeave(@RequestBody Leaves leaveRequest) {
        Leaves savedLeave = userService.saveLeaveRequest(leaveRequest);
        return ResponseEntity.ok(savedLeave);
    }
    @GetMapping("/fetchLeaves")
    public ResponseEntity<List<Leaves>> fetchLeaves() {
        return userService.fetchLeaves();  
    }
    

    
    @GetMapping("/product/{userId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable long userId) {
        User user = userService.getUserImage(userId);
        byte[] imageFile = user.getProfilePic();

        return ResponseEntity.ok().body(imageFile);

    }
    

  
  @PutMapping("/updateStatus/{id}")
  public ResponseEntity<String> updateStatus(@RequestParam("status") String status, @PathVariable("id") Long userId) {
      try {
          boolean isUpdated = userService.updateCheckInStatus(userId, status);
          if (isUpdated) {
              return ResponseEntity.ok("checkInStatus updated successfully!");
          } else {
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or status not updated");
          }
      } catch (Exception e) {
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating status");
      }
  }

   
    @GetMapping("/fetch/mobile/{mobile}")
    public ResponseEntity<User> fetchByEmail(@PathVariable String mobile) {
        User user = userService.fetchByMobile(mobile);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
        }
    }
    
    @PostMapping("/saveAttendance")
    public ResponseEntity<Attendance> saveAttendance(@RequestBody Attendance attendance) {
        try {
            Attendance savedAttendance = userService.saveAttendance(attendance);
            return ResponseEntity.ok(savedAttendance);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
