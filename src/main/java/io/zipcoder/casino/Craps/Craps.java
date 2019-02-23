package io.zipcoder.casino.Craps;

import java.util.Scanner;
import static java.lang.System.in;


public class Craps {
    public static void main(String[] args) {
        Scanner scan = new Scanner(in);
        Boolean isPassBet = false;
        Boolean isDontPassBet = false;
        Integer passBet;
        Integer dontPassBet;
        String passLineBet;
        Integer passLineAmount = 0;

        Integer comeOutRoll = 0;
        Integer point = 0;

        Boolean isComeBet = false;
        Boolean isDontComeBet = false;
        Integer comeBet;
        Integer dontComeBet;
        String comeLineBet;
        Integer comeLineAmount = 0;

        Integer pointRoll;
        //bet Integers have 'states': 0 = win, 1 = lose, 2 = push.

        System.out.println("Welcome to the High Roller Craps table.  Let's roll them bones!");
        System.out.println("Betting time.  Enter 'Pass' or 'Don't Pass'");
        passLineBet = scan.nextLine();
        if (passLineBet == "Pass" || passLineBet == "pass") {
            isPassBet = true;
        } else if (passLineBet == "Don't Pass" || passLineBet == "Don't pass" || passLineBet == "don't pass") {
            isDontPassBet = true;
        }
        System.out.println("Enter bet amount: ");
        passLineAmount = scan.nextInt();


        System.out.println("Press [Enter] to roll the dice");
        scan.nextLine();

        //comeOutRoll.roll();
        System.out.println("You rolled: " + comeOutRoll + ".");
        while (point == 0) {
            if (comeOutRoll == 7 || comeOutRoll == 11) {
                passBet = 0;
                dontPassBet = 1;
                point = 0;
            } else if (comeOutRoll == 2 || comeOutRoll == 3) {
                passBet = 1;
                dontPassBet = 0;
                point = 0;
            } else if (comeOutRoll == 12) {
                passBet = 1;
                dontPassBet = 2;
                point = 0;
            } else if (comeOutRoll == 4 || comeOutRoll == 5 || comeOutRoll == 6 || comeOutRoll == 8 || comeOutRoll == 9 || comeOutRoll == 10) {
                point = comeOutRoll;
                break;
            }
        }

        if (isPassBet == true && passBet == 0) {
            //add passLineAmount to playerWallet
            //remove passLineAmount from houseWallet
        } else if (isPassBet == true && passBet == 1) {
            //remove passLineAmount from playerWallet
            //add passLineAmount to houseWallet
        } else if (isDontPassBet == true && dontPassBet == 0) {
            //add passLineAmount to playerWallet
            //remove passLineAmount from houseWallet
        } else if (isDontPassBet == true && dontPassBet == 1) {
            //remove passLineAmount from playerWallet
            //add passLineAmount to houseWallet
        }


        System.out.println("Betting time.  Enter 'Come' or 'Don't Come'");
        comeLineBet = scan.nextLine();
        if (comeLineBet == "Come" || comeLineBet == "come") {
            isComeBet = true;
        } else if (comeLineBet == "Don't Come" || comeLineBet == "Don't come" || comeLineBet == "don't come") {
            isDontComeBet = true;
        }
        System.out.println("Enter bet amount: ");
        comeLineAmount = scan.nextInt();

        System.out.println("Press [Enter] to roll the dice");
        scan.nextLine();

        //pointRoll.roll();
        System.out.println("You rolled: " + pointRoll + ".");

        while (point != 0) {
            if (pointRoll == point) {
                comeBet = 0;
                dontComeBet = 1;
                point = 0;
            } else if (pointRoll == 7) {
                comeBet = 1;
                dontComeBet = 0;
                point = 0;
            } else if (pointRoll == 2 || pointRoll == 3) {
                comeBet = 1;
                dontComeBet = 0;
                point = 0;
            } else if (pointRoll == 12) {
                comeBet = 1;
                dontComeBet = 2;
                point = 0;
            } else if (pointRoll == 11) {
                comeBet = 0;
                dontComeBet = 1;
            } else {
                break;
            }
        }

        if (isComeBet == true && comeBet == 0) {
            //add comeLineAmount to playerWallet
            //remove comeLineAmount from houseWallet
        } else if (isComeBet == true && comeBet == 1) {
            //remove comeLineAmount from playerWallet
            //add comeLineAmount to houseWallet
        } else if (isDontComeBet == true && dontComeBet == 0) {
            //add passLineAmount to playerWallet
            //remove passLineAmount from houseWallet
        } else if (isDontComeBet == true && dontComeBet == 1) {
            //remove passLineAmount from playerWallet
            //add passLineAmount to houseWallet
        }
    }
}

