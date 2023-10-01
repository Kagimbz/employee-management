package com.emtech.employeemanagement.service;

import com.emtech.employeemanagement.data.EntityResponse;
import com.emtech.employeemanagement.data.departmentdata.DepartmentData;
import com.emtech.employeemanagement.data.departmentdata.DepartmentResponse;
import com.emtech.employeemanagement.model.department.Department;
import com.emtech.employeemanagement.model.employee.Employee;
import com.emtech.employeemanagement.repos.DepartmentRepository;
import lombok.NonNull;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Service
@Log
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public EntityResponse addDepartment(@NonNull String departmentName, @NonNull Integer code){
        AtomicReference<EntityResponse> response = new AtomicReference<>();

        departmentRepository.findByDepartmentName(departmentName).ifPresentOrElse(department -> {
            response.set(EntityResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message("Department with name " + departmentName + " already exists!")
                    .build());

            log.log(Level.WARNING, "Department with name %s already exists!", departmentName);
            },
                () -> {
            Department department = new Department();

            department.setDepartmentName(departmentName);

            department.setCode(code);

            departmentRepository.save(department);

            response.set(EntityResponse.builder()
                    .statusCode(HttpStatus.CREATED.value())
                    .message("Department created successfully.")
                    .build());
            }

        );
        return response.get();
    }

    public EntityResponse updateDepartmentName(@NonNull String departmentName, @NonNull String newDepartmentName){
        AtomicReference<EntityResponse> response = new AtomicReference<>();

        departmentRepository.findByDepartmentName(departmentName).ifPresentOrElse(department -> {
            department.setDepartmentName(newDepartmentName);

            departmentRepository.save(department);

            response.set(EntityResponse.builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Department name updated successfully.")
                    .build());
                },
                () -> {
            log.log(Level.WARNING, "Department with name %s does not exist!", departmentName);

            response.set(EntityResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message("Department with name " + departmentName + " does not exist!")
                    .build());
                }

        );
        return response.get();
    }

    public EntityResponse updateDepartmentCode(@NonNull String departmentName, @NonNull Integer code, @NonNull Integer newCode){
        AtomicReference<EntityResponse> response = new AtomicReference<>();

        departmentRepository.findByDepartmentName(departmentName).ifPresentOrElse(department -> {
            if (department.getCode().equals(code)) {
                department.setCode(newCode);
                departmentRepository.save(department);

                response.set(EntityResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Department code updated successfully.")
                        .build());
            } else {
                response.set(EntityResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message("Department with code " + code + " does not exist!")
                    .build());
            }
        },
        () -> {
                 log.log(Level.WARNING, "Department with name %s does not exist!", departmentName);

                 response.set(EntityResponse.builder()
                         .statusCode(HttpStatus.BAD_REQUEST.value())
                         .message("Department with name " + departmentName + " does not exist!")
                         .build());
              }

        );
        return response.get();
    }

    public DepartmentResponse getDepartmentByName(@NonNull String departmentName){
        AtomicReference<DepartmentResponse> response = new AtomicReference<>();

        departmentRepository.findByDepartmentName(departmentName).ifPresentOrElse(department -> {
            DepartmentData departmentData = DepartmentData.builder()
                    .departmentName(department.getDepartmentName())
                    .id(department.getId())
                    .employees(department.getEmployees())
                    .code(department.getCode())
                    .creationDate(department.getCreationDate())
                    .updateDate(department.getUpdateDate())
                    .build();

            response.set(DepartmentResponse.builder()
                    .statusCode(HttpStatus.OK.value())
                    .department(departmentData)
                    .build());
                },
                () -> log.log(Level.WARNING, "Department with name " + departmentName + " does not exist!")
        );
        return response.get();
    }

    public List<Employee> getEmployeesInDepartment(@NonNull String departmentName){
        AtomicReference<List<Employee>> employees = new AtomicReference<>();
        departmentRepository.findByDepartmentName(departmentName).ifPresentOrElse(department -> {
            employees.set(new ArrayList<Employee>(department.getEmployees()));
                },
                () -> log.log(Level.WARNING, "Department with name " + departmentName + " does not exist!")
        );
        return employees.get();
    }

    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public EntityResponse deleteDepartment(@NonNull String departmentName){
        AtomicReference<EntityResponse> response = new AtomicReference<>();

        departmentRepository.findByDepartmentName(departmentName).ifPresentOrElse(department -> {
            departmentRepository.delete(department);

            response.set(EntityResponse.builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Department deleted successfully.")
                    .build());
                },
                () -> {
            log.log(Level.WARNING, "Department with name " + departmentName + " does not exist!");

            response.set(EntityResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message("Department with name " + departmentName + " does not exist!")
                    .build());
                }
        );
        return response.get();
    }
}
