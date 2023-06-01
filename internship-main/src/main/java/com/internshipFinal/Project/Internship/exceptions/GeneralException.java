package com.internshipFinal.Project.Internship.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralException extends RuntimeException{

    private String message;
    private Object content;
}