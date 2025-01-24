package com.jmp.cloud.service.impl;

public class SubscriptionNotFoundError extends RuntimeException {
    public SubscriptionNotFoundError(String cardNumber) {
        super("Subscription not found for card number: " + cardNumber);
    }
}
