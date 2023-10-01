package com.emtech.employeemanagement.data.departmentdata;

import com.emtech.employeemanagement.model.employee.Employee;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class DepartmentData implements Serializable {
    @Builder.Default
    private Long id = null;

    @Builder.Default
    private String departmentName = null;

    @Builder.Default
    private List<Employee> employees = null;

    @Builder.Default
    private Integer code = null;

    @Builder.Default
    private Timestamp creationDate = null;

    @Builder.Default
    private Timestamp updateDate = null;
}
