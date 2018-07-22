package com.whitefire0;

// class Withdrawal represents an ATM withdrawal transaction
public class Withdrawal extends Transaction {

    private int amount; //int or double?
    private Keypad keypad;
    private CashDispenser cashDispenser;

    private final static int CANCELLED = 6;

    public Withdrawal(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser) {
        super(userAccountNumber, atmScreen, atmBankDatabase);

        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;

    }

    @Override
    public void execute() {
        boolean cashDispensed = false;
        double availableBalance;

        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        do {
            amount = displayMenuOfAmounts();

            if(amount != CANCELLED) {
                availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());

                if(amount <= availableBalance) {

                    if(cashDispenser.isSufficientCashAvailable(amount)) {
                        bankDatabase.debit(getAccountNumber(), amount);
                        cashDispenser.dispenseCash(amount);
                        cashDispensed = true;
                        screen.displayMessageLine("\nYour cash has been dispensed.");
                    } else {
                        screen.displayMessageLine("\nInsufficient cash available in the AMT\nPlease choose a smaller amount.");
                    }

                } else {
                    screen.displayMessageLine("\nInsufficient funds in your account.\nPlease choose a smaller amount.");
                }

            } else {
                screen.displayMessageLine("\nCancelling transaction...");
                return;
            }

        } while (!cashDispensed);
    }

    private int displayMenuOfAmounts() {
        int userChoice = 0;
        Screen screen = getScreen();
        int[] amounts = {0, 20, 40, 60, 100, 200};

        while(userChoice == 0) {
            screen.displayMessageLine("\nWithdrawal menu:");
            screen.displayMessageLine("\n1 - £20");
            screen.displayMessageLine("\n2 - £40");
            screen.displayMessageLine("\n3 - £60");
            screen.displayMessageLine("\n4 - £100");
            screen.displayMessageLine("\n5 - £200");
            screen.displayMessageLine("\n6 - Cancel transaction");
            screen.displayMessageLine("\nChoose a withdrawal amount: ");

            int input = keypad.getInput();

            switch (input) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    userChoice = amounts[input];
                    break;
                case CANCELLED:
                    userChoice = CANCELLED;
                    break;
                    default:
                        screen.displayMessageLine("\nInvalid selection. Try again.");
            }
        }

        return userChoice;
    }
}
