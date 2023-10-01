package com.emtech.employeemanagement.data.departmentdata;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class DepartmentResponse {
    @Builder.Default
    private DepartmentData department = null;

    @Builder.Default
    private Integer statusCode = HttpStatus.NOT_FOUND.value();
}
