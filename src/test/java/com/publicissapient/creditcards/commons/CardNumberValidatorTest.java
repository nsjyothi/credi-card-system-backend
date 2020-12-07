package com.publicissapient.creditcards.commons;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;


@RunWith(MockitoJUnitRunner.class)
public class CardNumberValidatorTest {


    private CardNumberValidator cardNumberValidator;

    @Before
    public void setup(){
        cardNumberValidator = new CardNumberValidator();
    }

    @Test
    public void cardNumberValidationForSuccessScenario() {
        //given valid card number
        String creditCardNumber = "4444333322221111";

        //when
        boolean result = cardNumberValidator.validateCardNumber(creditCardNumber);

        //then
        Assert.assertTrue(result);


    }

    @Test
    public void cardNumberValidationForFailureScenario() {
        //given invalid card number
        String creditCardNumber = "4444333322221121";

        //when
        boolean result = cardNumberValidator.validateCardNumber(creditCardNumber);

        //then

        Assert.assertFalse(result);


    }


}
