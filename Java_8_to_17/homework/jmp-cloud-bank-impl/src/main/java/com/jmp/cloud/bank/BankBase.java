package com.jmp.cloud.bank;

import com.jmp.bankapi.Bank;
import com.jmp.dto.*;

import java.util.function.BiFunction;

public class BankBase implements Bank {
   private int nextCardId = 1;

    @Override
    public BankCard createBankCard(User user, BiFunction<String, User, BankCard> ctor) {
        return ctor.apply(getNewCardId(), user);
    }

    private String getNewCardId() {
        return String.valueOf(nextCardId++);
    }
}
