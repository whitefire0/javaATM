package com.whitefire0;

public class Deposit extends Transaction {
    private double amount;
    private Keypad keypad;
    private DespositSlot depositSlot;
    private final static int CANCELLED = 0;

    public Deposit(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, DespositSlot atmDepositSlot) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        depositSlot = atmDepositSlot;
    }

    @Override
    public void execute() {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        amount = promptForDepositAmount();

        if(amount != CANCELLED) {
            screen.displayMessage("\nPlease insert an envelope containing ");
            screen.displayPoundAmount(amount);
            screen.displayMessageLine(".");

            boolean envelopeReceived = depositSlot.isEnvelopeRecieved();

            if(envelopeReceived) {
                screen.displayMessageLine("\nMoney received. Please allow 24hrs for the money to clear.");

                bankDatabase.credit(getAccountNumber(), amount);

            } else {
                screen.displayMessageLine("\nYou did not insert an envelope. Transaction cancelled.");
            }

        } else {
            screen.displayMessageLine("\nTransaction cancelled.");
        }
    }

    private double promptForDepositAmount() {
        Screen screen = getScreen();
        screen.displayMessage("\nPlease enter a deposit amount in CENTS (or 0 to cancel)");
        int input = keypad.getInput();

        if(input == CANCELLED) {
            return CANCELLED;
        } else {
            return (double) input / 100; //return £ amount
        }
    }
}
