package com.dad;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such order") // 404
public class OrderNotFoundException extends RuntimeException{

}
