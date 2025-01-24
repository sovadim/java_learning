package com.jmp.dto;

public class CreditBankCard extends BankCard {
    public CreditBankCard(String number, User user) {
        super(number, user);
    }

    @Override
    public String toString() {
        return "Credit card[" + number + "]";
    }
}
