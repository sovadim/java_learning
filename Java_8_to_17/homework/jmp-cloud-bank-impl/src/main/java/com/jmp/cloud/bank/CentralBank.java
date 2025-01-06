package com.jmp.cloud.bank;

import com.jmp.dto.BankCard;
import com.jmp.dto.BankCardType;
import com.jmp.dto.User;

public class CentralBank extends BankBase {
    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        throw new RuntimeException("Central bank does not issue bank cards");
    }
}
