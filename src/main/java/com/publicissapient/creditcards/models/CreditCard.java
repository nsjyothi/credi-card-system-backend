package com.publicissapient.creditcards.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Table
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    @Pattern(regexp = "[\\s]*[0-9]+",message="Credit Card Number should contain numbers only")
    @NotBlank (message = "Credit card number should not be null")
    @Size(min = 13, max = 19, message = "Credit card number length should be between 13 and 19")
    private String creditCardNumber;

    @Column
    private String accountNumber;

    @Column
    @NotBlank(message = "Customer name should not be null")
    private String customerName;

    @Column
    @NotNull(message = "Credit card limit should not be null")
    @DecimalMin(value = "0.01", message = "Credit Card limit should be provided")
    private Double creditCardLimit;
    @Column
    private Double balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(Double creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return id == that.id &&
                Double.compare(that.creditCardLimit, creditCardLimit) == 0 &&
                Double.compare(that.balance, balance) == 0 &&
                creditCardNumber.equals(that.creditCardNumber) &&
                Objects.equals(accountNumber, that.accountNumber) &&
                customerName.equals(that.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creditCardNumber, accountNumber, customerName, creditCardLimit, balance);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", limit=" + creditCardLimit +
                ", balance=" + balance +
                '}';
    }
}
