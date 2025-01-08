package com.jmp.app;

import com.jmp.bankapi.Bank;
import com.jmp.cloud.bank.BankFactory;
import com.jmp.cloud.bank.BankType;
import com.jmp.cloud.service.impl.CloudService;
import com.jmp.cloud.service.impl.SubscriptionNotFoundError;
import com.jmp.dto.*;
import com.jmp.service.api.Service;
import com.jmp.yet.another.service.impl.YetAnotherService;

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
    private static Service yetAnotherService = new YetAnotherService();

    public static void main(String[] args) {
        createBanks();
        createUsers();
        issueCards();
        makeSubscriptions();
        checkCreatedSubscriptions();
        checkAverageUsersAge();
        checkIsPayableUser();
        checkFindingSubscriptionsWIthPredicate();
        checkYetAnotherService();
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
                new User("Charlie", "Davis", LocalDate.of(1995, Month.AUGUST, 10)),
                new User("Tom", "Young", LocalDate.of(2008, Month.JUNE, 12)),
                new User("Lucy", "Green", LocalDate.of(2010, Month.NOVEMBER, 25))
        ));
        System.out.println("Created users: " + users);
    }

    public static void issueCards() {
        // Only retail bank can issue credit or debit cards
        try {
            centralBank.createBankCard(users.get(0), CreditBankCard::new);
        } catch (RuntimeException ignored) {
        }
        // Let's issue half credit cards and half debit cards
        users.stream()
                .limit(users.size() / 2)
                .forEach(user -> cards.add(retailBank.createBankCard(user, CreditBankCard::new)));
        users.stream()
                .skip(users.size() / 2)
                .forEach(user -> cards.add(retailBank.createBankCard(user, DebitBankCard::new)));
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
            value -> System.out.println(
                "Subscription, card: " + value.getBankCardNumber() + ", started at: " + value.getStartDate()
            )
        );

        // Check is non-existing subscription is not found
        String invalidCardNumber = "42";
        var nonExistingSubscription = cloudService.getSubscriptionByBankCardNumber(invalidCardNumber);
        nonExistingSubscription.ifPresentOrElse(
                value -> System.out.println("Subscription is unexpectedly not found"),
                () -> System.out.println("Subscription for card '42' is expectedly not found")
        );

        // Use special exception for non-existing subscription
        try {
            cloudService.getSubscriptionByBankCardNumber(invalidCardNumber)
                    .orElseThrow(() -> new SubscriptionNotFoundError(invalidCardNumber));
        } catch (Exception e) {
            System.out.println("Exception for not founding a subscription: " + e.getMessage());
        }
    }

    public static void checkAverageUsersAge() {
        System.out.println("Average service subscribers age: " + cloudService.getAverageUsersAge());
    }

    public static void checkIsPayableUser() {
        users.forEach(
            user -> System.out.println(
                "User " + user + " is payable: " + Service.isPayableUser(user) + ", birthday: " + user.getBirthday()
            )
        );
    }

    public static void checkFindingSubscriptionsWIthPredicate() {
        // Let's filter the subscriptions with those where the card number is more than 4
        System.out.println("Filtered subscriptions: ");
        cloudService.getAllSubscriptionsByCondition(s -> Integer.parseInt(s.getBankCardNumber()) > 4)
            .forEach((s) -> System.out.println(
                "Subscription, card: " + s.getBankCardNumber() + ", started at: " + s.getStartDate()
            )
        );
    }

    public static void checkYetAnotherService() {
        // The service should be same as CloudService, just using module loading
        // 1. Subscribe
        cards.forEach(yetAnotherService::subscribe);
        // 2. Check users
        System.out.println("Subscribed users by YetAnotherService: " +
                yetAnotherService.getAllUsers());
        // 3. Check subscription by card number
        System.out.println("Subscription by card number '1': " +
                yetAnotherService.getSubscriptionByBankCardNumber("1").get().getStartDate());
    }
}
