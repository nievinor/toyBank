package com.vostrikov;

import java.util.PriorityQueue;

public class Account {

//    PriorityQueue<Account>

//    private /*volatile*/ long balance;
//
//    public Account() {
//        this(0L);
//    }
//
//    public Account(long balance) {
//        this.balance = balance;
//    }
//
//    public long getBalance() {
//        return balance;
//    }
//
//    public void deposit(long amount) {
//        checkAmountNonNegative(amount);
//        balance += amount;
//    }
//
//    public void withdraw(long amount) {
//        checkAmountNonNegative(amount);
//        if (balance < amount) {
//            throw new IllegalArgumentException("not enough money");
//        }
//        balance -= amount;
//    }
//
//    private static void checkAmountNonNegative(long amount) {
//        if (amount < 0) {
//            throw new IllegalArgumentException("negative amount");
//        }
//    }

    private long balance;

    public Account() {
        this(0L);
    }

    public Account(long balance) {
        this.balance = balance;
    }

    public synchronized long getBalance() {
        return balance;
    }

    public synchronized void deposit(long amount) {
        checkAmountNonNegative(amount);
        balance += amount;
        notifyAll();
    }

    public synchronized void withdraw(long amount) {
        checkAmountNonNegative(amount);
        if (balance < amount) {
            throw new IllegalArgumentException("not enough money");
        }
        balance -= amount;
    }

    public synchronized void waitAndWithdraw(long amount) throws InterruptedException {
        checkAmountNonNegative(amount);
        while (balance < amount) {
            wait();
        }
        balance -= amount;
    }

    private static void checkAmountNonNegative(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("negative amount");
        }
    }
}
