package com.zapcom.advice;

import com.zapcom.constants.ClientServiceConstants;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/** Created by Rama Gopal Project Name - client-service */
@RestControllerAdvice
public class ClientServiceExceptionHandler {
  @ExceptionHandler(feign.RetryableException.class)
  public ResponseEntity<Object> handleFeignRetryableException(
      feign.RetryableException ex, WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ClientServiceConstants.TIME_STAMP, LocalDateTime.now());
    HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
    body.put(ClientServiceConstants.STATUS, status.value());
    body.put(ClientServiceConstants.ERROR, status.getReasonPhrase());
    body.put(
        ClientServiceConstants.MESSAGE, "Downstream service is not available: " + ex.getMessage());
    body.put(
        ClientServiceConstants.PATH, ((ServletWebRequest) request).getRequest().getRequestURI());
    return new ResponseEntity<>(body, status);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ClientServiceConstants.TIME_STAMP, LocalDateTime.now());
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    body.put(ClientServiceConstants.STATUS, status);
    body.put(ClientServiceConstants.ERROR, status.getReasonPhrase());
    body.put(ClientServiceConstants.MESSAGE, ex.getMessage());
    body.put(
        ClientServiceConstants.PATH, ((ServletWebRequest) request).getRequest().getRequestURI());
    return new ResponseEntity<>(body, status);
  }
}
