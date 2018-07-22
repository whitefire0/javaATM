package com.whitefire0;

public class BankDatabase {
    private Account[] accounts;

    public BankDatabase() {
        accounts = new Account[2]; //just two accounts for testing
        accounts[0] = new Account(12345, 1234,1000,1200);
        accounts[1] = new Account(56789, 4321, 500,600);
    }

    private Account getAccount(int accountNumber) {
        for(Account currentAccount : accounts) {
            if(currentAccount.getAccountNumber() == accountNumber) {
                return currentAccount;
            }
        }

        return null;
    }

    public boolean authenticateUser(int userAccountNumber, int userPIN) {
        Account userAccount = getAccount(userAccountNumber);

        if(userAccount != null) {
            return userAccount.validatePIN(userPIN);
        }
        else {
            return false;
        }
    }

    public double getAvailableBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getAvailableBalance();
    }

    public double getTotalBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getTotalBalance();
    }

    public void credit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).credit(amount);
    }

    public void debit(int userAccountNUmber, double amount) {
        getAccount(userAccountNUmber).debit(amount);
    }
}
