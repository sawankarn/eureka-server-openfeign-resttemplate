package com.example.departmentservice.feign;

import com.example.departmentservice.entity.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "departmentMock", url = "${client.mockapi.department}")
public interface DepartmentMockFeign {
    @GetMapping("/")
    public List<Department> findAllDepartmentMock();
}
