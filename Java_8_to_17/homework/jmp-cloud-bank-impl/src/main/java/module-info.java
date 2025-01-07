module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    requires jmp.dto;
    exports com.jmp.cloud.bank;
    provides com.jmp.bankapi.Bank with
        com.jmp.cloud.bank.BankBase,
        com.jmp.cloud.bank.CentralBank,
        com.jmp.cloud.bank.InvestmentBank,
        com.jmp.cloud.bank.RetailBank;
}