package com.emtech.employeemanagement.data.departmentdata;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class DepartmentCreateRequest {
    @Builder.Default
    private String departmentName = null;

    @Builder.Default
    private Integer code = null;
}
