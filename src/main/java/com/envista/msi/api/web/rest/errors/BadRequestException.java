package com.envista.msi.api.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid parameter user?")
public class BadRequestException extends RuntimeException
{
  public BadRequestException(String msg)
  {
    super(msg);
  }
}