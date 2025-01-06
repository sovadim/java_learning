package com.jmp.cloud.bank;

import com.jmp.bankapi.Bank;
import com.jmp.dto.*;

public class BankBase implements Bank {
   private int nextCardId = 1;

    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        return switch (bankCardType) {
            case CREDIT -> new CreditBankCard(getNewCardId(), user);
            case DEBIT -> new DebitBankCard(getNewCardId(), user);
        };
    }

    private String getNewCardId() {
        return String.valueOf(nextCardId++);
    }
}
