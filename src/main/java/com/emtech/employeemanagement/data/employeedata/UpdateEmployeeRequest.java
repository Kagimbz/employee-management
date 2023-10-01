package com.emtech.employeemanagement.data.employeedata;

import com.emtech.employeemanagement.model.department.Department;
import lombok.*;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateEmployeeRequest {
    @Builder.Default
    private Long id = null;

    @Builder.Default
    private String email= null;

    @Builder.Default
    private String departmentName = null;
}
