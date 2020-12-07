package com.publicissapient.creditcards.exception;

public class InvalidCreditCardNumberException extends RuntimeException{
    public InvalidCreditCardNumberException(String message){
        super(message);
    }
}
