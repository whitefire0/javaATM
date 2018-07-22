package com.whitefire0;

// class Withdrawal represents an ATM withdrawal transaction
public class Withdrawal {

    private int accountNumber;
    private double amount;

    // references to associated objects
    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private BankDatabase bankDatabase;

    public Withdrawal() { }

    public void exectute() { }
}
