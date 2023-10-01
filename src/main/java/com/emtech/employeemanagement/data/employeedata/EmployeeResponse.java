package com.emtech.employeemanagement.data.employeedata;

import com.emtech.employeemanagement.data.employeedata.EmployeeData;
import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class EmployeeResponse {
    @Builder.Default
    private EmployeeData employee = null;

    @Builder.Default
    private Integer statusCode = HttpStatus.NOT_FOUND.value();
}
