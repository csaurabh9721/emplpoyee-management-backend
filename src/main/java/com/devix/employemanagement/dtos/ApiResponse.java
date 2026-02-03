package com.devix.employemanagement.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private T body;
}

