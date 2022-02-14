package com.example.userservice.controller;

import com.example.userservice.dto.ResponseTemplateVO;
import com.example.userservice.dto.UserDepartmentDTO;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }


    @GetMapping("/")
    public List<UserDepartmentDTO> getAllUserDetailsWithUserAndDepartment(){
        return userService.getAllUserDetailsWithUserAndDepartment();
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userid){
        return userService.getUserWithDepartment(userid);
    }

}
