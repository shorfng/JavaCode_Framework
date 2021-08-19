package com.loto.project.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Author：蓝田_Loto
 * Date：2021-08-19 15:45
 * PageName：ValidateCodeException.java
 * Function：
 */

public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
