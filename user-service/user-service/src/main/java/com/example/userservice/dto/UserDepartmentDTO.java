package com.example.userservice.dto;

import com.example.userservice.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDepartmentDTO {
    private User user;
    private Department department;
}
