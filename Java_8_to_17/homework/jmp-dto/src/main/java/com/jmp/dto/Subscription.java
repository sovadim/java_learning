package com.jmp.dto;

import java.time.LocalDate;

public class Subscription {
    final String bankCardNumber;
    final LocalDate startDate;

    public Subscription(String bankCardNumber, LocalDate startDate) {
        this.bankCardNumber = bankCardNumber;
        this.startDate = startDate;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
