package org.pio.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.annotations.VisibleForTesting;

/**
 * <Add description here>
 * <p/>
 * Created 2/15/16.
 */
@ControllerAdvice
public class ErrorHandler {
    private static Logger LOG = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler({ RequiredServiceNotReachebleException.class })
    @VisibleForTesting
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleServiceNotAvailable
            (RequiredServiceNotReachebleException serviceNotAvailableException) {

        LOG.info("Handling serviceNotAvailableException {}", serviceNotAvailableException);
        return new ResponseEntity<>(
                new ErrorResponse(ErrorResponse.ERROR_TYPE.SERVICE_UNAVAILABLE,
                        serviceNotAvailableException.getModule().toString(), serviceNotAvailableException.getMessage()),
                HttpStatus.SERVICE_UNAVAILABLE);

    }

}
