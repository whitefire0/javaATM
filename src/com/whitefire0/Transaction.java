package com.whitefire0;

// abstract superclass represents an ATM transaction

public abstract class Transaction {
    private int accountNumber;
    private Screen screen;
    private BankDatabase bankDatabase;

    // transaction subclasses invoked by subclasses using super()
    public Transaction(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase) {
        accountNumber = userAccountNumber;
        screen = atmScreen;
        bankDatabase = atmBankDatabase;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public Screen getScreen() {
        return screen;
    }

    public BankDatabase getBankDatabase() {
        return bankDatabase;
    }

    //perform the transaction, overridden by each subclass
    abstract public void execute();
}
