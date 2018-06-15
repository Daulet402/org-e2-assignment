package org.e2.assessment.exception;

public class SystemException extends Exception {

    private ExceptionCode code;

    public SystemException() {
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
    }

    public enum ExceptionCode {
        USER_DOES_NOT_EXIST,
        ROLE_DOES_NOT_EXIST,
        USER_ALREADY_EXISTS,
        ROLE_ALREADY_EXISTS,
        USER_ALREADY_GRANTED,
        ROLE_ALREADY_GRANTED,
        USER_ALREADY_ASSIGNED_TO_ROLE,
    }

    public ExceptionCode getCode() {
        return code;
    }
}

