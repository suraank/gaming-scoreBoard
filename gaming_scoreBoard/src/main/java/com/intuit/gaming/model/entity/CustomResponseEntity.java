/*-Author------------------------------------
*- bibhas.abhishek@gmail.com
*- uber-onboarding: CustomResponseEntity
*- 26 Nov 2021 12:44 AM
---Made with <3 in Delhi,India---------------
---Details-----------------------------------
*- Links:
-------------------------------------------*/

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
