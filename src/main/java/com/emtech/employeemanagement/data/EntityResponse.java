package com.emtech.employeemanagement.data;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class EntityResponse {
    @Builder.Default
    private Integer statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

    @Builder.Default
    private String message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();

}
