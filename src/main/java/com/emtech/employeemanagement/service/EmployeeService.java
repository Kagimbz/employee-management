package com.emtech.employeemanagement.service;

import com.emtech.employeemanagement.data.EntityResponse;
import com.emtech.employeemanagement.data.employeedata.EmployeeData;
import com.emtech.employeemanagement.data.employeedata.EmployeeResponse;
import com.emtech.employeemanagement.model.employee.Employee;
import com.emtech.employeemanagement.repos.DepartmentRepository;
import com.emtech.employeemanagement.repos.EmployeeRepository;
import lombok.NonNull;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

@Service
@Log
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    public EntityResponse addEmployee(@NonNull String name, @NonNull String email, String departmentName){
        AtomicReference<EntityResponse> response = new AtomicReference<>();

        employeeRepository.findByEmail(email).ifPresentOrElse(employee -> {
            log.log(Level.WARNING, "Employee with email %s already exists", email);

            response.set(EntityResponse.builder()
                    .message("Employee with email " + email + " already exists")
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build());
            },
                () -> {
            departmentRepository.findByDepartmentName(departmentName).ifPresentOrElse(department ->
            {
                Employee employee = new Employee();
                employee.setName(name);
                employee.setEmail(email);
                employee.setDepartment(department);
                employee.setDepartmentName(departmentName);
                employee.setStatus(0);
                employeeRepository.save(employee);

                List<Employee> employees = department.getEmployees();
                employees.add(employee);
                department.setEmployees(employees);
                departmentRepository.save(department);

                response.set(EntityResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Employee added successfully")
                        .build());
            }, () -> {
                log.log(Level.WARNING, "Department does not exist!");
                response.set(EntityResponse.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message("Department does not exist!")
                        .build());
                    }
            );


        });
        return response.get();
    }

    public EntityResponse updateEmployeeEmail(@NonNull Long id, @NonNull String email){
        AtomicReference<EntityResponse> response = new AtomicReference<>();

        employeeRepository.findById(id).ifPresentOrElse(employee -> {
            employee.setEmail(email);

            employeeRepository.save(employee);

            response.set(EntityResponse.builder()
                    .message("Email updated successfully")
                    .statusCode(HttpStatus.OK.value())
                    .build());
            },
                () -> {
            log.log(Level.WARNING, "Employee with id %s does not exist", id);

            response.set(EntityResponse.builder()
                    .message("Employee with id " + id + " does not exist")
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build());
        });
        return response.get();
    }

    public EntityResponse updateEmployeeDepartment(@NonNull Long id, @NonNull String departmentName){
        AtomicReference<EntityResponse> response = new AtomicReference<>();

        employeeRepository.findById(id).ifPresentOrElse(employee -> {
            departmentRepository.findByDepartmentName(departmentName).ifPresentOrElse(department -> {
                employee.setDepartmentName(departmentName);
                employee.setDepartment(department);

                employeeRepository.save(employee);

                response.set(EntityResponse.builder()
                        .message("Department updated successfully")
                        .statusCode(HttpStatus.OK.value())
                        .build());
                    },
                    () -> {
                log.log(Level.WARNING, "Department with name " + departmentName + " does not exist!");
                response.set(EntityResponse.builder()
                        .message("Department with name " + departmentName + " does not exist!")
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build());
                    }

            );


            },
                () -> {
            log.log(Level.WARNING, "Employee with id %s does not exist", id);

            response.set(EntityResponse.builder()
                    .message("Employee with id " + id + " does not exist")
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build());
        });
        return response.get();
    }

    public EntityResponse updateEmployeeStatus(@NonNull Long id, @NonNull Integer statusCode){
        AtomicReference<EntityResponse> response = new AtomicReference<>();

        employeeRepository.findById(id).ifPresentOrElse(employee -> {
            employee.setStatus(statusCode);

            employeeRepository.save(employee);

            response.set(EntityResponse.builder()
                    .message("Status Updated successfully")
                    .statusCode(HttpStatus.OK.value())
                    .build());
            },
                () -> {
            log.log(Level.WARNING, "Employee with id %s does not exist", id);

            response.set(EntityResponse.builder()
                    .message("Employee with id " + id + " does not exist")
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build());
        });
        return response.get();
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesByStatus(@NonNull Integer statusCode){
        return employeeRepository.findByStatus(statusCode);
    }

    public EmployeeResponse getEmployeeById(@NonNull Long id){
        AtomicReference<EmployeeResponse> response = new AtomicReference<>();

        employeeRepository.findById(id).ifPresentOrElse(employee -> {
            EmployeeData employeeData = EmployeeData.builder()
                    .id(employee.getId())
                    .name(employee.getName())
                    .email(employee.getEmail())
                    .departmentName(employee.getDepartmentName())
                    .creationDate(employee.getCreationDate())
                    .updateDate(employee.getUpdateDate())
                    .status(employee.getStatus())
                    .build();

            response.set(EmployeeResponse.builder()
                    .employee(employeeData)
                    .statusCode(HttpStatus.OK.value())
                    .build());
            },
                () -> log.log(Level.WARNING, "Employee with id %s does not exist", id)
        );
        return response.get();
    }

    public EntityResponse deleteEmployee(@NonNull Long id){
        AtomicReference<EntityResponse> response = new AtomicReference<>();

        employeeRepository.findById(id).ifPresentOrElse(employee -> {
            employeeRepository.deleteById(id);

            response.set(EntityResponse.builder()
                    .message("Employee deleted successfully")
                    .statusCode(HttpStatus.OK.value())
                    .build());
            },
                () -> {
            log.log(Level.WARNING, "Employee with id %s does not exist", id);

            response.set(EntityResponse.builder()
                    .message("Employee with id " + id + " does not exist")
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build());
        });
        return response.get();
    }


}
