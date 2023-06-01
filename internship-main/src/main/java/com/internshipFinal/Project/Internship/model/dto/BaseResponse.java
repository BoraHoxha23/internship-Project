package com.internshipFinal.Project.Internship.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class BaseResponse {
    private List<String> messages;
}