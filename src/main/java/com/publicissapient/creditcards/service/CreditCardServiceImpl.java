package com.publicissapient.creditcards.service;

import com.publicissapient.creditcards.commons.CardNumberValidator;
import com.publicissapient.creditcards.exception.CreditCardNumberExists;
import com.publicissapient.creditcards.exception.InvalidCreditCardNumberException;
import com.publicissapient.creditcards.models.CreditCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.publicissapient.creditcards.repository.CreditCardRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Random;

@Component
public class CreditCardServiceImpl implements CreditCardService{
    private static final Logger LOG = LoggerFactory.getLogger(CreditCardServiceImpl.class);

    @Autowired
    CardNumberValidator cardNumberValidator;
    @Autowired
    CreditCardRepository repository;

    public CreditCard addCreditCard(CreditCard creditCard){
        LOG.info("Adding new credit card");
        try {
            if(cardNumberValidator.validateCardNumber(creditCard.getCreditCardNumber())){
                creditCard.setAccountNumber(generateAccountNumber());
                creditCard.setBalance(creditCard.getBalance()!= null?creditCard.getBalance():0.0);
                if(repository.findByCreditCardNumber(creditCard.getCreditCardNumber()) != null) {
                    LOG.error("Given credit number already allocated to a customer");
                    throw new CreditCardNumberExists("Given credit number already allocated to a customer");
                }
                repository.save(creditCard);
            }else{
                LOG.error("Given credit card number is invalid. Retry with valid credit card number");
                throw new InvalidCreditCardNumberException("Given credit card number is invalid. Retry with valid credit card number");
            }
        }
        catch (Exception e){
            LOG.error("Exception while adding new credit card "+e.getMessage());
            throw e;
        }
        return creditCard;
    }

    public List<CreditCard> getCreditCards() {
      LOG.info("Getting the existing credit card details");
      return (List <CreditCard>)repository.findAll();
    }

    private String generateAccountNumber(){
        Random generator = new Random();
        int i = generator.nextInt(9999);
        int j = generator.nextInt(9999);
        return String.valueOf(i).concat(String.valueOf(j));
    }

}
