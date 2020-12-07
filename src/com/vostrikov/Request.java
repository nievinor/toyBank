package com.vostrikov;

public class Request {

    private static int counter;

    private final Operation operation;
    private final long amount;
    private final int clientNumber;

    public Request(Operation operation, long amount) {
        this.operation = operation;
        this.amount = amount;
        clientNumber = ++counter;
    }

    public Operation getOperation() {
        return operation;
    }

    public long getAmount() {
        return amount;
    }

    public int getClientNumber() {
        return clientNumber;
    }
}
