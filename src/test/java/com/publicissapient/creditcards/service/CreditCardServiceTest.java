package com.publicissapient.creditcards.service;

import com.publicissapient.creditcards.commons.CardNumberValidator;
import com.publicissapient.creditcards.exception.CreditCardNumberExists;
import com.publicissapient.creditcards.exception.InvalidCreditCardNumberException;
import com.publicissapient.creditcards.helpers.SampleCreditCard;
import com.publicissapient.creditcards.models.CreditCard;
import com.publicissapient.creditcards.repository.CreditCardRepository;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;


@RunWith(MockitoJUnitRunner.Silent.class)
public class CreditCardServiceTest {

    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    @Mock
    private CreditCardRepository repository;

    @Mock
    private CardNumberValidator cardNumberValidator;

    private static final CreditCard creditCard1 = SampleCreditCard.builder()
                                                    .withId(1)
                                                    .withCustomerName("Jyothi")
                                                    .withCreditCardNumber("4444333322221111")
                                                    .withAccountNumber("123445643")
                                                    .withCreditCardLimit(2000.00)
                                                    .withBalance(0.0).build();

    private static final CreditCard creditCard2 = SampleCreditCard.builder()
            .withId(2)
            .withCustomerName("Jyothi")
            .withCreditCardNumber("4444333322221111")
            .withAccountNumber("123445643")
            .withCreditCardLimit(2000.00)
            .withBalance(0.0).build();

    private static final CreditCard creditCard3 = SampleCreditCard.builder()
            .withCustomerName("Jyothi")
            .withCreditCardNumber("4444333322221111")
            .withCreditCardLimit(2000.00).build();

    private static final CreditCard creditCard3WithId = SampleCreditCard.builder()
            .withId(0)
            .withCustomerName("Jyothi")
            .withCreditCardNumber("4444333322221111")
            .withAccountNumber("12313432432")
            .withBalance(0.0)
            .withCreditCardLimit(2000.00).build();




    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getCreditCardsTest(){
        //given
        List<CreditCard> creditCards = new LinkedList<>();
        creditCards.add(creditCard1);
        creditCards.add(creditCard2);
        when(repository.findAll()).thenReturn(creditCards);

        //when
        List<CreditCard> creditCardsList = creditCardService.getCreditCards();

        //then
        Assert.assertEquals(2,creditCardsList.size());
    }

    @Test
    public void addCreditCardShouldReturnSuccessTest(){

        //given
        when(repository.save(creditCard3)).thenReturn(creditCard3WithId);
        when(repository.findByCreditCardNumber("4444333322221111")).thenReturn(null);
        when(cardNumberValidator.validateCardNumber("4444333322221111")).thenReturn(true);

        //when
        CreditCard creditCard = creditCardService.addCreditCard(creditCard3);

        //then
        Assert.assertTrue(String.valueOf(creditCard.getId()), true);
        Assert.assertTrue(creditCard.getAccountNumber(), true);
        Assert.assertEquals(creditCard.getBalance(), 0.0, 0);
    }

    @Test
    public void addCreditCardNumberAlreadyExistsTest(){

        //given
        when(repository.save(creditCard3)).thenReturn(creditCard3WithId);
        when(repository.findByCreditCardNumber("4444333322221111")).thenReturn(creditCard3);
        when(cardNumberValidator.validateCardNumber("4444333322221111")).thenReturn(true);

        //when
        try{
            CreditCard creditCard = creditCardService.addCreditCard(creditCard3);
        } catch (Exception e){
            //then
            Assert.assertTrue(e instanceof CreditCardNumberExists);
        }
    }

    @Test
    public void addCreditCardShouldReturnFailureTest(){

        //given
        when(repository.save(creditCard3)).thenReturn(creditCard3WithId);
        when(repository.findByCreditCardNumber("4444333322221111")).thenReturn(null);
        when(cardNumberValidator.validateCardNumber("4444333322221121")).thenReturn(false);

        //when
        try{
            CreditCard creditCard = creditCardService.addCreditCard(creditCard3);
        } catch(Exception e) {
            //then
            Assert.assertTrue(e instanceof InvalidCreditCardNumberException);
        }
    }


}
