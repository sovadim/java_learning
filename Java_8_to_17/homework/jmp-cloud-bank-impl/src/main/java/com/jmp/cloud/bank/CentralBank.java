package com.jmp.cloud.bank;

import com.jmp.dto.BankCard;
import com.jmp.dto.User;

import java.util.function.BiFunction;

public class CentralBank extends BankBase {
    @Override
    public BankCard createBankCard(User user, BiFunction<String, User, BankCard> ctor) {
        throw new RuntimeException("Central bank does not issue bank cards");
    }
}
