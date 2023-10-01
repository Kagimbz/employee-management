package com.emtech.employeemanagement.repos;

import com.emtech.employeemanagement.model.employee.Employee;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(@NonNull String email);
    Optional<Employee> findById(@NonNull Long id);

    List<Employee> findByStatus(@NonNull Integer status);
}
