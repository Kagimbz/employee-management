package com.emtech.employeemanagement.service;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.emtech.employeemanagement.CustomAppender;
import com.emtech.employeemanagement.data.EntityResponse;
import com.emtech.employeemanagement.model.department.Department;
import com.emtech.employeemanagement.model.employee.Employee;
import com.emtech.employeemanagement.repos.DepartmentRepository;
import com.emtech.employeemanagement.repos.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.Level;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    private EmployeeService underTest;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        underTest = new EmployeeService(employeeRepository, departmentRepository);
    }

    @Test
    void canAddEmployeeSuccessfully() {
        // Given
        EntityResponse expectedResponse = EntityResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Employee added successfully")
                .build();

        Employee employee = new Employee();
        Department mockDepartment = mock(Department.class);

        String name = "Newts Kags";
        String email = "nkags@gmail.com";
        String deptName = "Software Engineering";

        employee.setName(name);
        employee.setEmail(email);
        employee.setDepartmentName(deptName);

        //When
        given(departmentRepository.findByDepartmentName(anyString())).willReturn(Optional.of(mockDepartment));
        EntityResponse actualResponse = underTest.addEmployee(name, email, deptName);

        //Then
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        Mockito.verify(employeeRepository).save(employeeArgumentCaptor.capture());
        Employee capturedEmployee = employeeArgumentCaptor.getValue();

        assertThat(capturedEmployee).isEqualTo(employee);
        assertThat(capturedEmployee).isExactlyInstanceOf(Employee.class);

        assertThat(actualResponse).isEqualTo(expectedResponse);

    }

    @Test
    void canGiveErrorIfEmailExistsWhenAddingEmployee() {
        // Given
        Employee mockEmployee = mock(Employee.class);

        CustomAppender customAppender = new CustomAppender();
        customAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger("com.emtech.employeemanagement.service.EmployeeService");
        logger.addAppender(customAppender);

        String name = "Newts Kags";
        String email = "nkags@gmail.com";
        String deptName = "Software Engineering";

        EntityResponse expectedResponse = EntityResponse.builder()
                .message("Employee with email " + email + " already exists")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();


        //When
        given(employeeRepository.findByEmail(anyString())).willReturn(Optional.of(mockEmployee));
        EntityResponse actualResponse = underTest.addEmployee(name, email, deptName);

        //Then
        List<ILoggingEvent> logs = customAppender.getLogs();

        for (ILoggingEvent log : logs){
            assertThat(log.getFormattedMessage()).isEqualTo(String.format("Employee with email %s already exists", email));
            assertThat(log.getLevel()).isEqualTo(Level.WARN);
        }

//        assertThat(logs)
//                .extracting(log -> log.getFormattedMessage(), log -> log.getLevel())
//                        .contains(tuple(String.format("Employee with email %s already exists", email), Level.WARN));

        Mockito.verify(employeeRepository, never()).save(any());

        assertThat(actualResponse).isEqualTo(expectedResponse);

    }

    @Test
    void canGiveErrorIfDeptDoesNotExistWhenAddingEmployee() {
        // Given
        EntityResponse expectedResponse = EntityResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Department does not exist!")
                .build();

        CustomAppender customAppender = new CustomAppender();
        customAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger("com.emtech.employeemanagement.service.EmployeeService");
        logger.addAppender(customAppender);

        String name = "Newts Kags";
        String email = "nkags@gmail.com";
        String deptName = "Software Engineering";


        //When
        EntityResponse actualResponse = underTest.addEmployee(name, email, deptName);

        //Then
        List<ILoggingEvent> logs = customAppender.getLogs();

        for (ILoggingEvent log : logs){
            assertThat(log.getFormattedMessage()).isEqualTo("Department does not exist!");
            assertThat(log.getLevel()).isEqualTo(Level.WARN);
        }

//        assertThat(logs)
//                .extracting(log -> log.getFormattedMessage(), log -> log.getLevel())
//                        .contains(tuple(String.format("Employee with email %s already exists", email), Level.WARN));

        Mockito.verify(employeeRepository, never()).save(any());

        assertThat(actualResponse).isEqualTo(expectedResponse);

    }

    @Test
    @Disabled
    void updateEmployeeEmail() {
    }

    @Test
    @Disabled
    void updateEmployeeDepartment() {
    }

    @Test
    @Disabled
    void updateEmployeeStatus() {
    }

    @Test
    void canFetchAllEmployees() {
        //When
        underTest.getAllEmployees();

        //Then
        Mockito.verify(employeeRepository).findAll();
    }

    @Test
    @Disabled
    void getEmployeesByStatus() {
    }

    @Test
    @Disabled
    void getEmployeeById() {
    }

    @Test
    @Disabled
    void deleteEmployee() {
    }
}