package com.whitefire0;

// represents an automated Teller machine
public class ATM {
    private boolean userAuthenticated;
    private int currentAccountNumber;
    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private DespositSlot depositSlot;
    private BankDatabase bankDatabase;

    // constants of main menu
    private static final int BALANCE_ENQUIRY = 1;
    private static final int WITHDRAWAL = 2;
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

    private void performTransactions() {
        Transaction currentTransaction = null;
        boolean userExited = false;

        while(!userExited) {
            int mainMenuSelection = displayMainMenu();

            switch (mainMenuSelection) {
                case BALANCE_ENQUIRY:
                case WITHDRAWAL:
                case DEPOSIT:
                    currentTransaction = createTransaction(mainMenuSelection);
                    currentTransaction.execute();
                    break;
                case EXIT:
                    screen.displayMessageLine("\nExiting the system...");
                    userExited = true;
                    break:
                default:
                    screen.displayMessageLine("\nYou did not enter a valid selection. Please try again.");
                    break;
            }

        }
    }

    private int displayMainMenu() {
        screen.displayMessageLine("\nMain Menu:");
        screen.displayMessageLine("\n1 - View balance");
        screen.displayMessageLine("\n2 - Withdraw cash");
        screen.displayMessageLine("\n3 - Deposit funds");
        screen.displayMessageLine("\n4 - Exit");
        screen.displayMessage("\nEnter a choice: ");
        return keypad.getInput();
    }

    private Transaction createTransaction(int type) {
        Transaction temp = null;

        switch (type) {
            case BALANCE_ENQUIRY:
                temp = new BalanceEnquiry(currentAccountNumber, screen, bankDatabase);
                break;
            case WITHDRAWAL:
                temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
                break;
            case DEPOSIT:
                temp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
                break;

        }

        return temp;
    }

}
