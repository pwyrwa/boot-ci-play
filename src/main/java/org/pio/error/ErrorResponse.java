package org.pio.error;

/**
 * <Add description here>
 * <p/>
 * Created 2/15/16.
 */
public class ErrorResponse {

    public enum ERROR_TYPE {
        SERVICE_UNAVAILABLE,
        INVALID_INPUT
    }


    private ERROR_TYPE errorType;
    private String module;
    private String message;

    public ErrorResponse(ERROR_TYPE errorType, String module) {
        this.errorType = errorType;
        this.module = module;
    }

    public ErrorResponse(ERROR_TYPE errorType, String module, String message) {
        this.errorType = errorType;
        this.module = module;
        this.message = message;
    }

    public ERROR_TYPE getErrorType() {
        return errorType;
    }

    public String getModule() {
        return module;
    }

    public String getMessage() {
        return message;
    }
}
