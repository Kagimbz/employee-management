package com.emtech.employeemanagement.controller;

import com.emtech.employeemanagement.data.EntityResponse;
import com.emtech.employeemanagement.data.departmentdata.DepartmentCreateRequest;
import com.emtech.employeemanagement.data.departmentdata.DepartmentResponse;
import com.emtech.employeemanagement.data.departmentdata.DepartmentUpdateRequest;
import com.emtech.employeemanagement.model.department.Department;
import com.emtech.employeemanagement.model.employee.Employee;
import com.emtech.employeemanagement.service.DepartmentService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping(path = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllDepartments(){
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok().body(departments);
    }

    @GetMapping(path = "/{departmentName}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentResponse> findDepartmentByName(@PathVariable String departmentName){
        DepartmentResponse department = departmentService.getDepartmentByName(departmentName);
        return ResponseEntity.ok().body(department);
    }

    @GetMapping(path = "/employees/{departmentName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findEmployeesInDepartment(@PathVariable String departmentName){
        List<Employee> employees = departmentService.getEmployeesInDepartment(departmentName);
        return ResponseEntity.ok().body(employees);
    }

    @PostMapping(path = "/new-department",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> createNewDepartment(@RequestBody DepartmentCreateRequest departmentCreateRequest){
        EntityResponse response = departmentService.addDepartment(departmentCreateRequest.getDepartmentName(), departmentCreateRequest.getCode());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(path = "/update-name",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> updateDepartmentName(@RequestBody DepartmentUpdateRequest departmentUpdateRequest){
        EntityResponse response = departmentService.updateDepartmentName(departmentUpdateRequest.getDepartmentName(), departmentUpdateRequest.getNewDepartmentName());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(path = "/update-code",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> updateDepartmentCode(@RequestBody DepartmentUpdateRequest departmentUpdateRequest){
        EntityResponse response = departmentService.updateDepartmentCode(departmentUpdateRequest.getDepartmentName(), departmentUpdateRequest.getCode(), departmentUpdateRequest.getNewCode());
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(path = "/delete/{departmentName}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> deleteDepartment(@PathVariable String departmentName){
        EntityResponse response = departmentService.deleteDepartment(departmentName);
        return ResponseEntity.ok().body(response);
    }
}
