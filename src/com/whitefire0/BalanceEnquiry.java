package com.whitefire0;

public class BalanceEnquiry extends Transaction {
    public BalanceEnquiry(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
    }

    @Override
    public void execute() {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
        double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());

        screen.displayMessageLine("\nBalance Information:");
        screen.displayMessage("- Available balance: ");
        screen.displayPoundAmount(availableBalance);
        screen.displayMessage("\n - Total Balance: ");
        screen.displayPoundAmount(totalBalance);
        screen.displayMessageLine("");
    }
}
