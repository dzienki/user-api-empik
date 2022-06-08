package com.dzienki.userapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceUnavailableException extends RuntimeException {

        public ServiceUnavailableException() {
            super();
        }

        public ServiceUnavailableException(Exception e) {
            super(e);
        }
}
