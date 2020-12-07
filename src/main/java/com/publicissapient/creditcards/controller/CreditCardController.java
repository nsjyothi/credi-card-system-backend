package com.publicissapient.creditcards.controller;

import com.publicissapient.creditcards.models.CreditCard;
import com.publicissapient.creditcards.service.CreditCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Api
@RestController
@RequestMapping("/")
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    @PostMapping(path = "/credit-cards", consumes = {"application/json"})
    @ApiOperation("Adds a new credit card")
    public CreditCard addCreditCard(@Valid @RequestBody CreditCard creditCard) {
        return creditCardService.addCreditCard(creditCard);
    }

    @GetMapping(path = "/credit-cards", produces = {"application/json"})
    @ApiOperation("Fetches all credit cards")
    public List<CreditCard> getCreditCards() {
        return creditCardService.getCreditCards();
    }
}
