package com.jmp.app;

import com.jmp.bankapi.Bank;
import com.jmp.cloud.bank.BankFactory;
import com.jmp.cloud.bank.BankType;
import com.jmp.cloud.service.impl.CloudService;
import com.jmp.dto.BankCard;
import com.jmp.dto.BankCardType;
import com.jmp.dto.User;
import com.jmp.service.api.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Bank retailBank;
    private static Bank centralBank;
    private static List<User> users = new ArrayList<>();
    private static List<BankCard> cards = new ArrayList<>();
    private static Service cloudService = new CloudService();

    public static void main(String[] args) {
        createBanks();
        createUsers();
        issueCards();
        makeSubscriptions();
        checkCreatedSubscriptions();
        checkAverageUsersAge();
    }

    public static void createBanks() {
         retailBank = BankFactory.createBank(BankType.RETAIL);
         centralBank = BankFactory.createBank(BankType.CENTRAL);
    }

    public static void createUsers() {
        // Add some users
        users.addAll(List.of(
                new User("John", "Doe", LocalDate.of(1992, Month.FEBRUARY, 29)),
                new User("Jane", "Smith", LocalDate.of(1985, Month.JULY, 15)),
                new User("Alice", "Johnson", LocalDate.of(1990, Month.DECEMBER, 5)),
                new User("Bob", "Brown", LocalDate.of(1988, Month.MARCH, 22)),
                new User("Charlie", "Davis", LocalDate.of(1995, Month.AUGUST, 10))
        ));
        System.out.println("Created users: " + users);
    }

    public static void issueCards() {
        // Only retail bank can issue credit or debit cards
        try {
            centralBank.createBankCard(users.get(0), BankCardType.CREDIT);
        } catch (RuntimeException ignored) {
        }
        // Let's issue half credit cards and half debit cards
        users.stream()
                .limit(users.size() / 2)
                .forEach(user -> cards.add(retailBank.createBankCard(user, BankCardType.CREDIT)));
        users.stream()
                .skip(users.size() / 2)
                .forEach(user -> cards.add(retailBank.createBankCard(user, BankCardType.DEBIT)));
        System.out.println("Created bank cards: " + cards);
    }

    public static void makeSubscriptions() {
        cards.forEach(cloudService::subscribe);
    }

    public static void checkCreatedSubscriptions() {
        // Check users
        System.out.println("Subscribed users: " + cloudService.getAllUsers());

        // Check if subscription can be find by card number
        var existingSubscription = cloudService.getSubscriptionByBankCardNumber(cards.get(0).getNumber());
        existingSubscription.ifPresent(
                value -> System.out.println("Subscription, card: " + value.getBankCardNumber() + ", started at: " + value.getStartDate())
        );

        // Check is non existing subscription is not found
        var nonExistingSubscription = cloudService.getSubscriptionByBankCardNumber("42");
        nonExistingSubscription.ifPresentOrElse(
                value -> System.out.println("Subscription is unexpectedly not found"),
                () -> System.out.println("Subscription for card '42' is expectedly not found")
        );
    }

    public static void checkAverageUsersAge() {
        System.out.println("Average service subscribers age: " + cloudService.getAverageUsersAge());
    }
}
