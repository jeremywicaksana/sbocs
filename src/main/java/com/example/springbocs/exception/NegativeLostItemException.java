package com.example.springbocs.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "claimed item has lower stock")
public class NegativeLostItemException extends RuntimeException {

    public NegativeLostItemException(String message) { super(message); }
}
