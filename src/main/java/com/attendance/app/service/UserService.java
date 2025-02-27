package com.attendance.app.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.attendance.app.exception.DuplicateException;
import com.attendance.app.model.Attendance;
import com.attendance.app.model.Employee;
import com.attendance.app.model.Interns;
import com.attendance.app.model.Leaves;
import com.attendance.app.model.User;
import com.attendance.app.repository.AttendanceRepository;
import com.attendance.app.repository.EmployeeRepository;
import com.attendance.app.repository.InternRepository;
import com.attendance.app.repository.LeaveRepository;
import com.attendance.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InternRepository internRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private LeaveRepository leaveRepository;

	@Autowired
	private ModelMapper modelMapper;


    public User getUserImage(long id) {
        return userRepository.findById(id).orElse(null);
    }

	public ResponseEntity<List<User>> fetchAll() {
		List<User> users = userRepository.findAll();
		return ResponseEntity.ok(users);
	}

    public User addUser(User user, MultipartFile imageFile) throws IOException {
        user.setImageName(imageFile.getOriginalFilename());
        user.setImageType(imageFile.getContentType());
        user.setProfilePic(imageFile.getBytes());
//        System.out.println("Gone");
//        System.out.println("Received file: " + (imageFile != null ? imageFile.getOriginalFilename() : "No file received"));
        return userRepository.save(user);
    }
	
	public Interns addIntern(Interns intern, MultipartFile imageFile) throws IOException  {
	if (internRepository.findByEmail(intern.getEmail()) != null
			|| internRepository.findByMobile(intern.getMobile()) != null) {
		throw new DuplicateException("User already exists!");
		}
		intern.setImageName(imageFile.getOriginalFilename());
		intern.setImageType(imageFile.getContentType());
		intern.setProfilePic(imageFile.getBytes());
//    	System.out.println("Gone");
//    	System.out.println("Received file: " + (imageFile != null ? imageFile.getOriginalFilename() : "No file received"));

		return internRepository.save(intern);
	}
	
	public Employee addEmployee(Employee employee, MultipartFile imageFile) throws IOException  {
	if (employeeRepository.findByEmail(employee.getEmail()) != null
			|| internRepository.findByMobile(employee.getMobile()) != null) {
		throw new DuplicateException("User already exists!");
		}
		employee.setImageName(imageFile.getOriginalFilename());
		employee.setImageType(imageFile.getContentType());
		employee.setProfilePic(imageFile.getBytes());
//    	System.out.println("Gone");
//    	System.out.println("Received file: " + (imageFile != null ? imageFile.getOriginalFilename() : "No file received"));

		return employeeRepository.save(employee);
	}
	
	
    public Leaves saveLeaveRequest(Leaves leaveRequest) {
        try {
        	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             if (leaveRequest.getStartDate() != null && leaveRequest.getEndDate() != null) {
                 leaveRequest.setStartDate(dateFormat.parse(dateFormat.format(leaveRequest.getStartDate())));
                 leaveRequest.setEndDate(dateFormat.parse(dateFormat.format(leaveRequest.getEndDate())));
             }
        } catch (Exception e) {
            throw new RuntimeException("Error parsing date", e);
        }
        return leaveRepository.save(leaveRequest);
    }
    
    
	public ResponseEntity<List<Leaves>> fetchLeaves() {
		List<Leaves> leaves = leaveRepository.findAll();
		return ResponseEntity.ok(leaves);
	}
	
	public boolean updateCheckInStatus(Long userId, String status) {
	    Optional<User> userOptional = userRepository.findById(userId);
	    if (userOptional.isPresent()) {
	        User user = userOptional.get();
	        user.setCheckInStatus(status);
	        userRepository.save(user);
	        return true;
	    }
	    return false;
	}


	 public User fetchByMobile(String mobile) {
	        return userRepository.findByMobile(mobile); 
	    }
	 
	    @Autowired
	    private AttendanceRepository attendanceRepository;

	    public Attendance saveAttendance(Attendance attendance) {
	        return attendanceRepository.save(attendance);
	    }


	
	
	

}
