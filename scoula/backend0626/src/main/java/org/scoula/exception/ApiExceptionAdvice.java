package org.scoula.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Order(2)  // CommonExceptionAdvice 다음 순서
public class ApiExceptionAdvice {
    // 기존 API 관련 예외 처리 로직(400, 404, 500 등)
}