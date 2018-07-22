package com.whitefire0;

// class Withdrawal represents an ATM withdrawal transaction
public class Withdrawal extends Transaction {

    private int accountNumber;
    private double amount;

    // references to associated objects
    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private BankDatabase bankDatabase;

    public Withdrawal() { }

    @Override
    public void exectute() { }
}
