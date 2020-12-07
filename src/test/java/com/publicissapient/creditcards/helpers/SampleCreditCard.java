package com.publicissapient.creditcards.helpers;

import com.publicissapient.creditcards.models.CreditCard;

public class SampleCreditCard {
    private int id;
    private String customerName;
    private String creditCardNumber;
    private String accountNumber;
    private Double creditCardLimit;
    private Double balance;

    public static SampleCreditCard builder(){
        return new SampleCreditCard();
    }

    public SampleCreditCard withId(int id){
        this.id=id;
        return this;
    }

    public SampleCreditCard withCustomerName(String customerName){
        this.customerName = customerName;
        return this;
    }

    public SampleCreditCard withCreditCardNumber(String creditCardNumber){
        this.creditCardNumber = creditCardNumber;
        return this;
    }

    public SampleCreditCard withAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
        return this;
    }

    public SampleCreditCard withCreditCardLimit(Double creditCardLimit){
        this.creditCardLimit = creditCardLimit;
        return this;
    }

    public SampleCreditCard withBalance(Double balance){
        this.balance = balance;
        return this;
    }

    public CreditCard build() {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(this.id);
        creditCard.setCustomerName(this.customerName);
        creditCard.setCreditCardNumber(this.creditCardNumber);
        creditCard.setAccountNumber(this.accountNumber);
        creditCard.setCreditCardLimit(this.creditCardLimit);
        creditCard.setBalance(this.balance==null?0.0:this.balance);
        return creditCard;
    }

}
