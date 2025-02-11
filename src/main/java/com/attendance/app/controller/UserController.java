package com.attendance.app.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.attendance.app.model.User;
import com.attendance.app.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody User user) {
//        User newUser = userService.registerUser(user);
//        return ResponseEntity.ok("User registered successfully!");
//    }
//    
//    
//
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
    
    
    @GetMapping("/product/{userId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable long userId) {
        User user = userService.getUserImage(userId);
        byte[] imageFile = user.getProfilePic();

        return ResponseEntity.ok().body(imageFile);

    }
//
//    
//    @GetMapping("/fetch")
//    public ResponseEntity<List<User>> fetch() {
//        return userService.fetchAll();  
//    }
//    
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.ok("User deleted successfully!");
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
//        User user = userService.updateUser(id, updatedUser);
//        return ResponseEntity.ok("User updated successfully!");
//    }
//    
//    
    @GetMapping("/fetch/mobile/{mobile}")
    public ResponseEntity<User> fetchByEmail(@PathVariable String mobile) {
        User user = userService.fetchByMobile(mobile);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
        }
    }
//    
//
//    
//    @PutMapping("/updateProfileByEmail/{email}")
//    public ResponseEntity<String> updateUserByEmail(@PathVariable String email, @RequestBody User updatedUser) {
//        User user = userService.updateUserByEmail(email, updatedUser);
//        return ResponseEntity.ok("User updated successfully!");
//    }
//    
//    @PutMapping("/updatePasswordByEmail")
//    public ResponseEntity<String> updateUserPasswordByEmail(
//            @RequestParam String email, 
//            @RequestParam String newPassword) {
//        userService.updateUserPasswordByEmail(email, newPassword);
//        return ResponseEntity.ok("Password updated successfully!");
//    }
//
//
//    
////    @PostMapping("/signin")
////    public ResponseEntity<String> signin(@RequestBody User user) {
////        User loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());
////        return ResponseEntity.ok("Signin successful!");  
////    }
//    
//    
//    @PostMapping("/signin")
//    public ResponseEntity<?> signin(@RequestBody User user) {
//        try {
//            // Attempt to log in the user
//            User loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());
//
//            // Create a response map
//            Map<String, Object> response = new HashMap<>();
//            response.put("message", "Signin successful!");
//            response.put("user", loggedInUser);
//
//            return ResponseEntity.ok(response);
//
//        } catch (ResourceNotFound e) {
//            // Handle invalid credentials
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
//        } catch (Exception e) {
//            // Handle other exceptions
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                                 .body("An error occurred during signin.");
//        }
//    }

}
