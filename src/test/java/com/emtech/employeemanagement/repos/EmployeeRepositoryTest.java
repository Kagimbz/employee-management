package com.emtech.employeemanagement.repos;

import com.emtech.employeemanagement.model.employee.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository repo;

    @AfterEach
    void tearDown() {
        repo.deleteAll();
    }

    @Test
    void shouldCheckIfEmployeeExistsByEmail() {
        // Given
        String email = "nkags@gmail.com";

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Newts Kags");
        employee.setEmail(email);

        repo.save(employee);

        // When
        boolean check = repo.checkIfExistsByEmail(email);

        // Then
        assertThat(check).isTrue();
    }

    @Test
    void shouldCheckIfEmployeeDoesNotExistByEmail() {
        // Given
        String email = "nkags@gmail.com";

        // When
        boolean check = repo.checkIfExistsByEmail(email);

        // Then
        assertThat(check).isFalse();
    }
}