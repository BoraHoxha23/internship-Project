package com.internshipFinal.Project.Internship.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler {
    public void commence(HttpServletRequest request, HttpServletResponse response, GeneralException exception)
            throws IOException {

//        log.error("General Exception!");
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        response.setContentType(APPLICATION_JSON_VALUE);
//        new ObjectMapper().writeValue(response.getOutputStream());
    }
}