package com.jmp.cloud.service.impl;

import com.jmp.dto.BankCard;
import com.jmp.dto.Subscription;
import com.jmp.dto.User;
import com.jmp.service.api.Service;

import java.time.LocalDate;
import java.util.*;

public class CloudService implements Service {
    private final HashSet<User> users = new HashSet<>();
    private final HashSet<Subscription> subscriptions = new HashSet<>();

    @Override
    public void subscribe(BankCard bankCard) {
        users.add(bankCard.getUser());
        subscriptions.add(new Subscription(bankCard.getNumber(), LocalDate.now()));
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return subscriptions.stream()
                .filter(s -> s.getBankCardNumber().equals(cardNumber))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return users.stream().toList();
    }
}
