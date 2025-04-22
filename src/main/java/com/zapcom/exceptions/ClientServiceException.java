package com.zapcom.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/** Created by Rama Gopal Project Name - client-service */
@Getter
@AllArgsConstructor
public class ClientServiceException extends RuntimeException {

  private final String errorCode;
  private final HttpStatus httpStatus;
  private final String message;
}
