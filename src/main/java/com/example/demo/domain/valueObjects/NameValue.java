package com.example.demo.domain.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class NameValue {

    private String value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public NameValue(String value) {
        if(value == null || value.isEmpty()){
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if ( value.length() > 30) {
            throw new IllegalArgumentException("El nombre no puede ser mayor a 30 caracteres");
        }
        if ( value.length() < 2) {
            throw new IllegalArgumentException("El nombre no puede ser menor a 2 caracteres");
        }
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
