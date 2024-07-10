
package com.intuit.gaming.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CustomResponseEntity {
    private HttpStatus httpStatus;
    private Object data;
    private String message;
    private long timestamp;
}
