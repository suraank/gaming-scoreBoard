package com.intuit.gaming.factory;

import com.intuit.gaming.model.entity.CustomResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomResponseEntityFactory {
    public CustomResponseEntity getSuccessResponse(Object data) {
        return CustomResponseEntity.builder()
                .httpStatus(HttpStatus.OK)
                .data(data)
                .message(HttpStatus.OK.getReasonPhrase())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public CustomResponseEntity getBadRequestResponse(String errorMessage) {
        return CustomResponseEntity.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .data(null)
                .message(errorMessage)
                .timestamp(System.currentTimeMillis())
                .build();
    }
    public CustomResponseEntity getISEResponse() {
        return CustomResponseEntity.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .data(null)
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public CustomResponseEntity getNotFoundResponse(String message) {
        return CustomResponseEntity.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .data(null)
                .message(message)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public CustomResponseEntity getCreatedResponse(Object data) {
        return CustomResponseEntity.builder()
                .httpStatus(HttpStatus.CREATED)
                .data(data)
                .message(HttpStatus.CREATED.getReasonPhrase())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public CustomResponseEntity getConflictResponse(String message) {
        return CustomResponseEntity.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .data(null)
                .message(message)
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
