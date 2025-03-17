package com.gattyspaintings.webshop.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StandardRoleNotFoundException extends RuntimeException {
    public StandardRoleNotFoundException() {
        super("Standard role not found");
    }
}
