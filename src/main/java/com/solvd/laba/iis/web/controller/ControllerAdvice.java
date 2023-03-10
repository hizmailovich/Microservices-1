package com.solvd.laba.iis.web.controller;

import com.solvd.laba.iis.domain.exception.AuthenticationException;
import com.solvd.laba.iis.domain.exception.ResourceAlreadyExistException;
import com.solvd.laba.iis.domain.exception.ResourceDoesNotExistException;
import com.solvd.laba.iis.domain.exception.ResourceMappingException;
import com.solvd.laba.iis.web.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceMappingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleResourceMappingException(ResourceMappingException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        log.error(ex.getMessage(), ex);
        return errorDto;
    }

    @ExceptionHandler(ResourceDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleResourceDoesNotExistException(ResourceDoesNotExistException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        log.error(ex.getMessage(), ex);
        return errorDto;
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleResourceAlreadyExistsException(ResourceAlreadyExistException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        log.error(ex.getMessage(), ex);
        return errorDto;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorDto> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorDto(error.getObjectName() + "." + error.getField(), error.getDefaultMessage()))
                .toList();
        log.error(ex.getMessage(), ex);
        return errors;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        log.error(ex.getMessage(), ex);
        return errorDto;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDto handleJwtAuthenticationException(AuthenticationException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        log.error(ex.getMessage(), ex);
        return errorDto;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorDto handleAccessDeniedException(AccessDeniedException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        log.error(ex.getMessage(), ex);
        return errorDto;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        log.error(ex.getMessage(), ex);
        return errorDto;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Please, try again");
        log.error(ex.getMessage(), ex);
        return errorDto;
    }

}
