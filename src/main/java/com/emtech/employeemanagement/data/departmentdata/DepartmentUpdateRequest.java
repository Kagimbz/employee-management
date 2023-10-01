package com.emtech.employeemanagement.data.departmentdata;

import lombok.*;
import lombok.extern.java.Log;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class DepartmentUpdateRequest {
    @Builder.Default
    private String departmentName = null;

    @Builder.Default
    private String newDepartmentName = null;

    @Builder.Default
    private Integer code = null;

    @Builder.Default
    private Integer newCode = null;
}
