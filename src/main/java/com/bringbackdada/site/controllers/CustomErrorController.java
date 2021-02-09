package com.bringbackdada.site.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {

        // TODO send error messages via mail to admin
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-505";
            }

            if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "error-400";            }
        }

        return "error-nocode";
    }


    @Override
    public String getErrorPath() {
        return null;
    }
}
