package com.jmp.dto;

public class BankCard {
    final String number;
    final User user;

    public BankCard(String number, User user) {
        this.number = number;
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public User getUser() {
        return user;
    }
}
