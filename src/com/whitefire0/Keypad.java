package com.whitefire0;

import java.util.Scanner;

// represents keypad of ATM
public class Keypad {
    private Scanner input;

    public Keypad() {
        input = new Scanner(System.in);
    }

    public int getInput() {
        return input.nextInt(); //assumes integer
    }
}
