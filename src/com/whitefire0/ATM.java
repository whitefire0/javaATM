package com.whitefire0;

// represents an automated Teller machine
public class ATM {
    private boolean userAuthenticated;
    private int currentAccountNumber;
    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private DespositSlot despositSlot;
    private BankDatabase bankDatabase;

    // constants of main menu
    private static final int BALANCE_ENQUIRY = 1;
    private static final int WTIHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;

    public ATM() {
        userAuthenticated = false;
        currentAccountNumber = 0;
        screen = new Screen();
        keypad = new Keypad();
        cashDispenser = new CashDispenser();
        despositSlot = new DepositSlot();
        bankDatabase = new BankDatabase();
    }

    public void run() {
        // welcome and authenticate
        while (true) {
            while (!userAuthenticated) {
                screen.displayMessageLine("\nWelcome!");
                authenticateUser();
            }

            performTransactions();
            userAuthenticated = false; //reset before next ATM session
            currentAccountNumber = 0;
            screen.displayMessageLine("\nThank you, have a nice day!");
        }
    }

    private void authenticateUser() {
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput();
        screen.displayMessage("\nEnter you PIN: ");
        int pin = keypad.getInput();

        userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
        if (!userAuthenticated) {
            currentAccountNumber = accountNumber;
        }
        else {
            screen.displayMessageLine("\nInvalid account number of PIN. Please try again.");
        }

    }

}
