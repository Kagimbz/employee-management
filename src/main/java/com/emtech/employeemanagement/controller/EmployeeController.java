package com.emtech.employeemanagement.controller;

import com.emtech.employeemanagement.data.employeedata.AddEmployeeRequest;
import com.emtech.employeemanagement.data.employeedata.EmployeeResponse;
import com.emtech.employeemanagement.data.EntityResponse;
import com.emtech.employeemanagement.data.employeedata.UpdateEmployeeRequest;
import com.emtech.employeemanagement.model.employee.Employee;
import com.emtech.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/all")
    public ResponseEntity<List<Employee>> findAllEmployees(){
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok().body(allEmployees);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/active-employees")
    public ResponseEntity<?> findActiveEmployees(){
        List<Employee> activeEmployees = employeeService.getEmployeesByStatus(1);
        return ResponseEntity.ok().body(activeEmployees);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/inactive-employees")
    public ResponseEntity<?> findInactiveEmployees(){
        List<Employee> inactiveEmployees = employeeService.getEmployeesByStatus(0);
        return ResponseEntity.ok().body(inactiveEmployees);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{employee_id}")
    public ResponseEntity<EmployeeResponse> findEmployeeById(@PathVariable Long employee_id){
        EmployeeResponse response = employeeService.getEmployeeById(employee_id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/add")
    public ResponseEntity<EntityResponse> addEmployee(@RequestBody AddEmployeeRequest addEmployeeRequest){
        EntityResponse response = employeeService.addEmployee(addEmployeeRequest.getName(), addEmployeeRequest.getEmail(), addEmployeeRequest.getDepartmentName());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/update-email")
    public ResponseEntity<EntityResponse> updateEmployeeEmail(@RequestBody UpdateEmployeeRequest updateEmployeeRequest){
        EntityResponse response = employeeService.updateEmployeeEmail(updateEmployeeRequest.getId(), updateEmployeeRequest.getEmail());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/update-department")
    public ResponseEntity<EntityResponse> updateEmployeeDepartment(@RequestBody UpdateEmployeeRequest updateEmployeeRequest){
        EntityResponse response = employeeService.updateEmployeeDepartment(updateEmployeeRequest.getId(), updateEmployeeRequest.getDepartmentName());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/activate/{employee_id}")
    public ResponseEntity<EntityResponse> activateEmployee(@PathVariable Long employee_id) {
        EntityResponse response = employeeService.updateEmployeeStatus(employee_id, 1);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/deactivate/{employee_id}")
    public ResponseEntity<EntityResponse> deactivateEmployee(@PathVariable Long employee_id) {
        EntityResponse response = employeeService.updateEmployeeStatus(employee_id, 0);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/delete/{employee_id}")
    public ResponseEntity<EntityResponse> deleteEmployee(@PathVariable Long employee_id){
        EntityResponse response = employeeService.deleteEmployee(employee_id);
        return ResponseEntity.ok().body(response);
    }

}
