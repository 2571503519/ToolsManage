package com.jack.web.advice;

import com.jack.util.TmResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Jackaroo Zhang on 2018/10/25.
 */
//@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public TmResponse handle(IllegalArgumentException e) {
        return TmResponse.fail(e.getMessage());
    }

}
