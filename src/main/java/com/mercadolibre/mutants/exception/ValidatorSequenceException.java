package com.mercadolibre.mutants.exception;

public class ValidatorSequenceException extends Exception{

    private String message;

    public ValidatorSequenceException(String message){
        this.message = message;
    }

    public ValidatorSequenceException() {

    }

    @Override
    public String getMessage(){
        return message;
    }
}
