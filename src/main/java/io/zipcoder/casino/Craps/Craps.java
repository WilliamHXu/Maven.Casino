package io.zipcoder.casino.Craps;

import io.zipcoder.casino.Interfaces.GamblingGame;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.utilities.Dice;
import io.zipcoder.casino.utilities.Player;

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
        Integer passLineBet;
        Integer passLineAmount = 0;

        Integer comeOutRoll = 0;
        Integer point = 0;

        Boolean isComeBet = false;
        Boolean isDontComeBet = false;
        Integer comeBet = 0;
        Integer dontComeBet = 0;
        Integer comeLineBet = 0;
        Integer comeLineAmount = 0;

        Integer pointRoll = 0;
        //bet Integers have 'states': 1 = win, 2 = lose, 3 = push.

        System.out.println("Welcome to the High Roller Craps table.  Let's roll them bones!");
        System.out.println("");


        while (point == 0) {

            System.out.println("Betting time.");
            System.out.println("Enter 1 for 'Pass'");
            System.out.println("Enter 2 for 'Don't Pass'");

            while(!scan.hasNextInt()) {
                scan.next();
                System.out.println("Enter 1 or 2");
            }


            passLineBet = scan.nextInt();
            if (passLineBet == 1) {
                isPassBet = true;
                System.out.println("Betting on the 'Pass Line'.");
            } else if (passLineBet == 2) {
                isDontPassBet = true;
                System.out.println("Betting on the 'Don't Pass Line'.");
            }

            System.out.println("");
            System.out.println("Enter bet amount: ");
            while(!scan.hasNextInt()) {
                scan.next();
                System.out.println("Enter Bet Amount");
            }
            passLineAmount = scan.nextInt();


            System.out.println("Press [Enter] to roll the dice");
            scan.nextLine();
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
                System.out.println("Craps. 'Pass' loses, 'Don't Pass' wins.");
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

        while (point != 0 && comeLineBet == 0) {
            System.out.println("");
            System.out.println("Betting time.");
            System.out.println("Enter 1 for 'Come'");
            System.out.println("Enter 2 for 'Don't Come'");

            while(!scan.hasNextInt()) {
                scan.next();
                System.out.println("Enter 1 or 2");
            }
                comeLineBet = scan.nextInt();
                if (comeLineBet == 1) {
                    isComeBet = true;
                    System.out.println("Betting on the 'Come Line'.");
                } else if (comeLineBet == 2) {
                    isDontComeBet = true;
                    System.out.println("Betting on the 'Don't Come Line'.");
                } else {
                    System.out.println("Select an option");

                }

            System.out.println("");
            System.out.println("Enter bet amount: ");

            while(!scan.hasNextInt()) {
                scan.next();
                System.out.println("Enter Bet Amount");
            }
            comeLineAmount = scan.nextInt();

            scan.nextLine();


            while (point != 0) {
                //Additional Bets will go here
                System.out.println("Press [Enter] to roll the dice");
                scan.nextLine();


                pointRoll = crapsDice.throwAndSum();
                System.out.println("You rolled: " + pointRoll + ".");


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
                    System.out.println("'Come' loses, 'Don't Come' wins. Keep rolling");
                } else if (pointRoll == 12) {
                    comeBet = 2;
                    dontComeBet = 3;
                    System.out.println("'Come' loses, 'Don't Come' pushses. Keep rolling");
                } else if (pointRoll == 11) {
                    comeBet = 1;
                    dontComeBet = 2;
                    System.out.println("'Come' wins, 'Don't Come' loses. Keep rolling");
                } else if (pointRoll != point && (pointRoll == 4 || pointRoll == 5 || pointRoll == 6 || pointRoll == 8 || pointRoll == 9 || pointRoll == 10)) {
                    System.out.println("Keep rolling");
                } else {
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

    public Boolean playAgain() {
        return null;
    }

    public void exit() {
        System.exit(0);
    }
}