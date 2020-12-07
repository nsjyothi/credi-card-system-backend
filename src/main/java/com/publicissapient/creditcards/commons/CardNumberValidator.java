package com.publicissapient.creditcards.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CardNumberValidator {
    private static final Logger LOG = LoggerFactory.getLogger(CardNumberValidator.class);
    public boolean validateCardNumber(String cardNumber){
        LOG.info("Validating credit card number");
        int numberOfDigits = cardNumber.length();

        int sum = 0;
        boolean isSecondDigit = false;
        for (int i = numberOfDigits - 1; i >= 0; i--)
        {
            int num = cardNumber.charAt(i) - '0';

            if (isSecondDigit == true)
                num = num * 2;

            sum += num / 10;
            sum += num % 10;

            isSecondDigit = !isSecondDigit;
        }
        return (sum % 10 == 0);
    }
}
