package com.publicissapient.creditcards.repository;
import org.springframework.data.repository.CrudRepository;
import com.publicissapient.creditcards.models.CreditCard;

public interface CreditCardRepository extends CrudRepository<CreditCard,Integer> {
    public CreditCard findByCreditCardNumber(String creditCardNumber);
}
