package com.publicissapient.creditcards.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicissapient.creditcards.helpers.SampleCreditCard;
import com.publicissapient.creditcards.models.CreditCard;
import com.publicissapient.creditcards.service.CreditCardService;
import com.publicissapient.creditcards.service.CreditCardService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class CreditCardControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CreditCardService creditCardService;


    CreditCard creditCard;

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

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        creditCard = new CreditCard();
        creditCard.setCustomerName("Jyothi");
        creditCard.setCreditCardNumber("4444333322221111");
        creditCard.setCreditCardLimit(1000.00);
    }

    @Test
    public void addCreditCard() throws Exception{

        //given
        creditCard = new CreditCard();
        creditCard.setCustomerName("Jyothi");
        creditCard.setCreditCardNumber("4444333322221111");
        creditCard.setCreditCardLimit(1000.00);
        String uri = "/credit-cards";
        ObjectMapper mapper = new ObjectMapper();

       CreditCard creditCard1 = new CreditCard();
       creditCard1.setCreditCardLimit(1000.00);
       creditCard1.setCustomerName("Jyothi");
       creditCard1.setCreditCardNumber("4444333322221111");
       creditCard1.setAccountNumber("12345566");

        when(creditCardService.addCreditCard(creditCard)).thenReturn(creditCard1);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(creditCard))).andReturn();

        //then
        Assert.assertEquals(200,mvcResult.getResponse().getStatus());
        CreditCard content = mapper.readValue(mvcResult.getResponse().getContentAsString(), CreditCard.class);
        Assert.assertTrue(String.valueOf(content.getId()), true);
        Assert.assertTrue(content.getAccountNumber(), true);
        Assert.assertEquals(content.getBalance(), 0.0, 0);
    }

    @Test
    public void getCreditCards() throws Exception{
        //given
        String uri = "/credit-cards";
        List<CreditCard> creditCards = new LinkedList<>();
        creditCards.add(creditCard1);
        creditCards.add(creditCard2);

        when(creditCardService.getCreditCards()).thenReturn(creditCards);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        //then
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        Assert.assertEquals(2, creditCards.size());

    }
}
