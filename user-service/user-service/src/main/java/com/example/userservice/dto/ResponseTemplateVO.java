package com.example.userservice.dto;

import com.example.userservice.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ResponseTemplateVO {
    private User user;
    private Department department;
}
