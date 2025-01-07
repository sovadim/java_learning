package com.jmp.bankapi;

import com.jmp.dto.BankCard;
import com.jmp.dto.User;

import java.util.function.BiFunction;

public interface Bank {
    BankCard createBankCard(User user, BiFunction<String, User, BankCard> ctor);
}
