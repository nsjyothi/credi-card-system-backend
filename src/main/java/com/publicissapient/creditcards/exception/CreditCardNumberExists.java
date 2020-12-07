package com.publicissapient.creditcards.exception;

public class CreditCardNumberExists extends RuntimeException{
    public CreditCardNumberExists(String message){
        super(message);
    }
}
