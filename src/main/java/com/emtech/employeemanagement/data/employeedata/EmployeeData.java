package com.emtech.employeemanagement.data.employeedata;

import com.emtech.employeemanagement.model.department.Department;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeData implements Serializable {
    @Builder.Default
    private Long id = null;

    @Builder.Default
    private String name = null;

    @Builder.Default
    private String email = null;

//    @Builder.Default
//    private Department department = null;

    @Builder.Default
    private String departmentName = null;

    @Builder.Default
    private Timestamp creationDate = null;

    @Builder.Default
    private Timestamp updateDate = null;

    @Builder.Default
    private Integer status = null;
}
