package com.example.demo.view;

import com.example.demo.Dto.ErrorRsponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResponseBuilder {
    private ResponseBuilder() {
    };
    private static List<ErrorRsponseDto> getCustomError(BindingResult result) {
        List<ErrorRsponseDto> dtoList = new ArrayList<>();
        result.getFieldErrors().forEach(fieldError -> {
            ErrorRsponseDto dto = ErrorRsponseDto.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage()).build();
            dtoList.add(dto);
        });
        return dtoList;
    }

    public static Response getFailureResponse(BindingResult result, String message) {
        return Response.builder()
                .message(message)
                .errors(getCustomError(result))
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date().getTime())
                .build();
    }

    public static Response getFailureResponse(HttpStatus status, String message) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .timestamp(new Date().getTime())
                .build();
    }

    public static Response getSuccessResponse(HttpStatus status, String message, Object content) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .statusCode(status.value())
                .timestamp(new Date().getTime())
                .build();
    }

    public static Response getSuccessResponse(HttpStatus status, String message, Object content, int numberOfElement) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .numberOfElement(numberOfElement)
                .statusCode(status.value())
                .timestamp(new Date().getTime())
                .build();
    }

    public static Response getSuccessResponse(HttpStatus status, String message, Object content, int numberOfElement, long rowCount) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .numberOfElement(numberOfElement)
                .statusCode(status.value())
                .rowCount(rowCount)
                .timestamp(new Date().getTime())
                .build();
    }




}
