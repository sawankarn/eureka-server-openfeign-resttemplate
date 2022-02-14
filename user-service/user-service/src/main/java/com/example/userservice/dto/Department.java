package com.example.userservice.dto;

import lombok.Data;

@Data
public class Department {
    private Long departmentId;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
