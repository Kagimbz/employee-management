package com.emtech.employeemanagement;

import com.emtech.employeemanagement.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2Repo extends JpaRepository<Employee, Long> {
}
