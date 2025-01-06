package com.jmp.cloud.bank;

import com.jmp.bankapi.Bank;

public class BankFactory {
    public static Bank createBank(BankType bankType) {
        return switch (bankType) {
            case RETAIL -> new RetailBank();
            case INVESTMENT -> new InvestmentBank();
            case CENTRAL -> new CentralBank();
        };
    }
}
