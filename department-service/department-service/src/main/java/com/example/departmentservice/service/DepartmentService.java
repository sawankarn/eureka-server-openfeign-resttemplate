package com.example.departmentservice.service;

import com.example.departmentservice.dto.APIResponse;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.feign.DepartmentMockFeign;
import com.example.departmentservice.repository.DepartmentRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMockFeign departmentMockFeign;

    @PostConstruct
    public void initDB(){
//        List<Department> departmentList = departmentMockFeign.findAllDepartmentMock();
        Faker faker = new Faker();
        List<Department> departmentList = IntStream.range(1, 100)
                .mapToObj(i->new Department((long) i,faker.name().fullName(),faker.address().streetAddress(),faker.address().zipCode()))
                .collect(Collectors.toList());
        
        departmentRepository.saveAll(departmentList);
    }

    public List<Department> findAllDepartmentList(){
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList;
//        return new APIResponse<>(departmentList.size(), departmentList);
    }

    //Sorting
    public APIResponse<List<Department>> findDepartmentWithSorting(String field){
        List<Department> departmentList = departmentRepository.findAll(Sort.by(Sort.Direction.ASC, field));
        return new APIResponse<>(departmentList.size(), departmentList);
    }

    //Pagination
    public APIResponse<Page<Department>> findDepartmentWithPagination(int offset, int pageSize){
        Page<Department> departmentPage = departmentRepository.findAll(PageRequest.of(offset, pageSize));
        return new APIResponse<>(departmentPage.getSize(), departmentPage);
    }

    //Paging with Sorting
    public APIResponse<Page<Department>> findDepartmentWithPaginationWithSorting(int offset, int pageSize, String field) {
        Page<Department> departmentPage = departmentRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return new APIResponse<>(departmentPage.getSize(), departmentPage);
    }

    public List<Department> findAllDepartment(){
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department department){
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId){
        return departmentRepository.findByDepartmentId(departmentId);
    }

    public Department updateDepartmentWithId(Long id, String departmentName) {
        Department department = departmentRepository.findByDepartmentId(id);
        department.setDepartmentName(departmentName);
        return departmentRepository.save(department);
    }

    public Department upadteDepartmentWithMap(Long id, Map<Object, Object> objectMap) {
        Department department = departmentRepository.findByDepartmentId(id);
        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Department.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, department, value);
        });
        return departmentRepository.save(department);
    }


}
