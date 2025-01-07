package com.jmp.dto;

public class DebitBankCard extends BankCard {
    public DebitBankCard(String number, User user) {
        super(number, user);
    }

    @Override
    public String toString() {
        return "Debit card[" + number + "]";
    }
}
