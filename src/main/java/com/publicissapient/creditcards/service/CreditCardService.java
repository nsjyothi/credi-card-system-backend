package com.publicissapient.creditcards.service;

import com.publicissapient.creditcards.models.CreditCard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CreditCardService {

    CreditCard addCreditCard(CreditCard creditCard);
    List<CreditCard> getCreditCards();
}
