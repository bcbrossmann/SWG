package com.sg.dvdlibrarymvc.validation;

public class ValidationError {

    private String fieldName;
    private String message;

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

    public ValidationError(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
