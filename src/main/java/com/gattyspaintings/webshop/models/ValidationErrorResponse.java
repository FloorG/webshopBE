package com.gattyspaintings.webshop.models;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class ValidationErrorResponse {
    public String timestamp;
    public int status;
    public String error;
    public String path;
    public Map<String, String> reasons;
}
