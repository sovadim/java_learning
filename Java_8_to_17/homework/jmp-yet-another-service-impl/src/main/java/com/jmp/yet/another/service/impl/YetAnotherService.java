package com.jmp.yet.another.service.impl;

import com.jmp.dto.BankCard;
import com.jmp.dto.Subscription;
import com.jmp.dto.User;
import com.jmp.service.api.Service;

import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.function.Predicate;

public class YetAnotherService implements Service {
    private final Service service;

    public YetAnotherService() {
    this.service = ServiceLoader
            .load(Service.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Service implementation not found"));
    }

    @Override
    public void subscribe(BankCard bankCard) {
        service.subscribe(bankCard);
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return service.getSubscriptionByBankCardNumber(cardNumber);
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
        return service.getAllSubscriptionsByCondition(condition);
    }

    @Override
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }
}
