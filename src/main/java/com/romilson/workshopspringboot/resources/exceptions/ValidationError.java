package com.romilson.workshopspringboot.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ValidationError extends StandarError {

    @Getter
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer value, String message, Long currentTimeMillis) {
        super(value, message, currentTimeMillis);
    }

    public void addError(String field, String message){
        errors.add(new FieldMessage(field, message));
    }
}
