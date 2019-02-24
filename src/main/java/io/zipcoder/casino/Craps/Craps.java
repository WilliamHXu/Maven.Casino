package io.zipcoder.casino.Craps;

import io.zipcoder.casino.Interfaces.GamblingGame;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.utilities.Dice;

import java.sql.SQLOutput;
import java.util.Scanner;
import static java.lang.System.in;


public class Craps {
    public static void main(String[] args) {
        Dice crapsDice = new Dice(2);

        Scanner scan = new Scanner(in);
        Boolean isPassBet = false;
        Boolean isDontPassBet = false;
        Integer passBet = 0;
        Integer dontPassBet = 0;
        String passLineBet;
        Integer passLineAmount = 0;

        Integer comeOutRoll = 0;
        Integer point = 0;

        Boolean isComeBet = false;
        Boolean isDontComeBet = false;
        Integer comeBet = 0;
        Integer dontComeBet = 0;
        String comeLineBet = "";
        Integer comeLineAmount = 0;

        Integer pointRoll = 0;
        //bet Integers have 'states': 1 = win, 2 = lose, 3 = push.

        System.out.println("Welcome to the High Roller Craps table.  Let's roll them bones!");


        while (point == 0) {

            System.out.println("Betting time.  Enter 'Pass' or 'Don't Pass'.");
            passLineBet = scan.nextLine();
            if (passLineBet.equals("Pass") || passLineBet.equals("pass")) {
                isPassBet = true;
                System.out.println("Betting on the 'Pass Line'.");
            } else if (passLineBet.equals("Don't Pass") || passLineBet.equals("Don't pass") || passLineBet.equals("don't pass")) {
                isDontPassBet = true;
                System.out.println("Betting on the 'Don't Pass Line'.");
            } else {
                System.out.println("Select 'Pass' or 'Don't Pass'.");
            }

            System.out.println("Enter bet amount: ");
            passLineAmount = scan.nextInt();


            System.out.println("Press [Enter] to roll the dice");
            scan.nextLine();

            comeOutRoll = crapsDice.throwAndSum();
            System.out.println("You rolled: " + comeOutRoll + ".");

            if (comeOutRoll == 7 || comeOutRoll == 11) {
                passBet = 1;
                dontPassBet = 2;
                point = 0;
                System.out.println("Good shooting.  'Pass' wins, 'Don't Pass' loses");
                break;
            } else if (comeOutRoll == 2 || comeOutRoll == 3) {
                passBet = 2;
                dontPassBet = 1;
                point = 0;
                System.out.println("Craps. 'Pass' loses, 'Don't pass' wins.");
                break;
            } else if (comeOutRoll == 12) {
                passBet = 2;
                dontPassBet = 3;
                point = 0;
                System.out.println("Boxcars. 'Pass' loses, 'Don't Pass' pushes.");
                break;
            } else if (comeOutRoll == 4 || comeOutRoll == 5 || comeOutRoll == 6 || comeOutRoll == 8 || comeOutRoll == 9 || comeOutRoll == 10) {
                point = comeOutRoll;
                System.out.println("Point set at: " + point + ".");
                break;
            }
        }

        if (isPassBet == true && passBet == 1) {
            System.out.println("1");
            //add passLineAmount to playerWallet
            //remove passLineAmount from houseWallet
        } else if (isPassBet == true && passBet == 2) {
            System.out.println("2");
            //remove passLineAmount from playerWallet
            //add passLineAmount to houseWallet
        } else if (isDontPassBet == true && dontPassBet == 1) {
            System.out.println("3");
            //add passLineAmount to playerWallet
            //remove passLineAmount from houseWallet
        } else if (isDontPassBet == true && dontPassBet == 2) {
            System.out.println("4");
            //remove passLineAmount from playerWallet
            //add passLineAmount to houseWallet
        }

        while (point != 0 && comeLineBet == "") {
            System.out.println("Betting time.  Enter 'Come' or 'Don't Come'");
            comeLineBet = scan.nextLine();
            if (comeLineBet.equals("Come") || comeLineBet.equals("come")) {
                isComeBet = true;
                System.out.println("Betting on the 'Come Line'.");
            } else if (comeLineBet.equals("Don't Come") || comeLineBet.equals("Don't come") || comeLineBet.equals("don't come")) {
                isDontComeBet = true;
                System.out.println("Betting on the 'Don't Come Line'.");
            } else {
                System.out.println("Select 'Come' or 'Don't come'");
            }
            System.out.println("Enter bet amount: ");
            comeLineAmount = scan.nextInt();

            System.out.println("Press [Enter] to roll the dice");
            scan.nextLine();

            pointRoll = crapsDice.throwAndSum();
            System.out.println("You rolled: " + pointRoll + ".");
        }
        while (point != 0) {


            if (pointRoll == point) {
                comeBet = 1;
                dontComeBet = 2;
                System.out.println("Hit the Point.  'Come' wins, 'Don't Come' loses.");
                point = 0;
                break;
            } else if (pointRoll == 7) {
                comeBet = 2;
                dontComeBet = 1;
                point = 0;
                System.out.println("Seven'd out.  'Come' loses, 'Don't Come' wins.");
                break;
            } else if (pointRoll == 2 || pointRoll == 3) {
                comeBet = 2;
                dontComeBet = 1;
                point = 0;
                System.out.println("Keep rolling");
                break;
            } else if (pointRoll == 12) {
                comeBet = 2;
                dontComeBet = 3;
                System.out.println("Keep rolling");
                break;
            } else if (pointRoll == 11) {
                comeBet = 1;
                dontComeBet = 2;
                System.out.println("Keep rolling");
                break;
            } else if (pointRoll != point && (pointRoll == 4 || pointRoll == 5 || pointRoll == 6 || pointRoll == 8 || pointRoll == 9 || pointRoll == 10)) {
                System.out.println("Keep rolling");
                break;
            }

        }

        if (isComeBet == true && comeBet == 1) {
            //add comeLineAmount to playerWallet
            //remove comeLineAmount from houseWallet
        } else if (isComeBet == true && comeBet == 2) {
            //remove comeLineAmount from playerWallet
            //add comeLineAmount to houseWallet
        } else if (isDontComeBet == true && dontComeBet == 1) {
            //add comeLineAmount to playerWallet
            //remove comeLineAmount from houseWallet
        } else if (isDontComeBet == true && dontComeBet == 2) {
            //remove passLineAmount from playerWallet
            //add comeLineAmount to houseWallet
        }
    }
    }


