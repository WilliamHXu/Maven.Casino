package io.zipcoder.casino.Craps;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Dice;
import io.zipcoder.casino.utilities.Player;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;


public class CrapsGame implements Game {

    private Console inOut = new Console(System.in, System.out);
    private Casino casinoObject = new Casino();

    private ArrayList<Player> playerList = new ArrayList<Player>(); //playerList from Player class
    private ArrayList<CrapsPlayer> playerListCraps = new ArrayList<CrapsPlayer>(); //Craps player list
    CrapsPlayer crapsPlayer;
    Integer houseCommission;
    Integer houseAmount = 0;

    public CrapsGame(ArrayList<Player> playerList) {
        this.playerList = playerList;
        for (Player player : playerList) {
            crapsPlayer = new CrapsPlayer(player);
            if (crapsPlayer.getWallet() > 0) {
                playerListCraps.add(crapsPlayer);
            }
        }
    }

    public CrapsGame(Player player){
        this.crapsPlayer = new CrapsPlayer(player);
    }


    public void start() {

        // Initial State Instance Variables
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

        // Display players state before game start
        inOut.println("Below are the list of Players eligible to bet and play Craps: ");
        for (CrapsPlayer player : playerListCraps) {
            inOut.println("Player Name: " + player.getName() +
                    ", wallet Balance: " + player.getWallet());
        }

        //Greeting
        inOut.println("");
        inOut.println("Welcome to the High Roller Craps table.  Let's roll them bones!");
        inOut.println("");


        while (point == 0) {

            // First Bet Loop
            // firstBetLoop();
            inOut.println("Betting time.");
            inOut.println("Enter 1 for 'Pass'");
            inOut.println("Enter 2 for 'Don't Pass'");
            inOut.println("Enter 3 to Skip Betting");

            while (!scan.hasNextInt()) {
                scan.next();
                inOut.println("Enter 1, 2, or 3");
            }


            passLineBet = scan.nextInt();
            if (passLineBet == 1) {
                isPassBet = true;
                inOut.println("Betting on the 'Pass Line'.");
            } else if (passLineBet == 2) {
                isDontPassBet = true;
                inOut.println("Betting on the 'Don't Pass Line'.");
            }

            inOut.println("");


            inOut.println("Enter bet amount");
            while (!scan.hasNextInt()) {
                scan.next();
                inOut.println("Enter bet amount");
            }
            passLineAmount = scan.nextInt();


            // Prompt
            inOut.println("Press [Enter] to roll the dice");
            scan.nextLine();
            scan.nextLine();

            comeOutRoll = crapsDice.throwAndSum();
            inOut.println("You rolled: " + comeOutRoll + ".");

            //checkComeOutRoll
            if (comeOutRoll == 7 || comeOutRoll == 11) {
                passBet = 1;
                dontPassBet = 2;
                point = 0;
                inOut.println("Good shooting.  'Pass' wins, 'Don't Pass' loses");
                break;
            } else if (comeOutRoll == 2 || comeOutRoll == 3) {
                passBet = 2;
                dontPassBet = 1;
                point = 0;
                inOut.println("Craps. 'Pass' loses, 'Don't Pass' wins.");
                break;
            } else if (comeOutRoll == 12) {
                passBet = 2;
                dontPassBet = 3;
                point = 0;
                inOut.println("Boxcars. 'Pass' loses, 'Don't Pass' pushes.");
                break;
            } else if (comeOutRoll == 4 || comeOutRoll == 5 || comeOutRoll == 6 || comeOutRoll == 8 || comeOutRoll == 9 || comeOutRoll == 10) {
                point = comeOutRoll;
                inOut.println("Point set at: " + point + ".");
                break;
            }
        }
        //passLinePayout
        if (isPassBet == true && passBet == 1) {
            //add passLineAmount to playerWallet
            crapsPlayer.setPlayerWalletBalance(passLineAmount);
            //remove passLineAmount from houseWallet
            houseAmount -= comeLineAmount;
            inOut.println("Balance: " + crapsPlayer.getWallet());
            inOut.println("");
        } else if (isPassBet == true && passBet == 2) {
            //remove passLineAmount from playerWallet
            crapsPlayer.setPlayerWalletBalance(-(passLineAmount));
            //add passLineAmount to houseWallet
            houseAmount += comeLineAmount;
            inOut.println("Balance: " + crapsPlayer.getWallet());
            inOut.println("");
        } else if (isDontPassBet == true && dontPassBet == 1) {
            //add passLineAmount to playerWallet
            crapsPlayer.setPlayerWalletBalance(passLineAmount);
            //remove passLineAmount from houseWallet
            houseAmount -= comeLineAmount;
            inOut.println("Balance: " + crapsPlayer.getWallet());
            inOut.println("");
        } else if (isDontPassBet == true && dontPassBet == 2) {
            //remove passLineAmount from playerWallet
            crapsPlayer.setPlayerWalletBalance(-(passLineAmount));
            //add passLineAmount to houseWallet
            houseAmount += comeLineAmount;
            inOut.println("Balance: " + crapsPlayer.getWallet());
            inOut.println("");
        }
        //Come Line Bet
        while (point != 0 && comeLineBet == 0) {
            inOut.println("");
            inOut.println("Betting time.");
            inOut.println("Enter 1 for 'Come'");
            inOut.println("Enter 2 for 'Don't Come'");
            inOut.println("Enter 3 to Skip Betting");

            while (!scan.hasNextInt()) {
                scan.next();
                inOut.println("Enter 1, 2, or 3");
            }
            comeLineBet = scan.nextInt();
            if (comeLineBet == 1) {
                isComeBet = true;
                inOut.println("Betting on the 'Come Line'.");
            } else if (comeLineBet == 2) {
                isDontComeBet = true;
                inOut.println("Betting on the 'Don't Come Line'.");
            } else {
                inOut.println("Select an option");

            }

            inOut.println("");
            inOut.println("Enter bet amount: ");

            while (!scan.hasNextInt()) {
                scan.next();
                inOut.println("Enter bet amount");
            }
            comeLineAmount = scan.nextInt();

            scan.nextLine();


            while (point != 0) {
                //Additional Bets will go here
                inOut.println("Press [Enter] to roll the dice");
                scan.nextLine();


                pointRoll = crapsDice.throwAndSum();
                inOut.println("You rolled: " + pointRoll + ".");


                if (pointRoll == point) {
                    comeBet = 1;
                    dontComeBet = 2;
                    inOut.println("Hit the Point.  'Come' wins, 'Don't Come' loses.");
                    point = 0;
                    break;
                } else if (pointRoll == 7) {
                    comeBet = 2;
                    dontComeBet = 1;
                    point = 0;
                    inOut.println("Seven'd out.  'Come' loses, 'Don't Come' wins.");
                    break;
                } else if (pointRoll == 2 || pointRoll == 3) {
                    comeBet = 2;
                    dontComeBet = 1;
                    inOut.println("'Come' loses, 'Don't Come' wins. Keep rolling");
                } else if (pointRoll == 12) {
                    comeBet = 2;
                    dontComeBet = 3;
                    inOut.println("'Come' loses, 'Don't Come' pushses. Keep rolling");
                } else if (pointRoll == 11) {
                    comeBet = 1;
                    dontComeBet = 2;
                    inOut.println("'Come' wins, 'Don't Come' loses. Keep rolling");
                } else if (pointRoll != point && (pointRoll == 4 || pointRoll == 5 || pointRoll == 6 || pointRoll == 8 || pointRoll == 9 || pointRoll == 10)) {
                    inOut.println("Keep rolling");
                } else {
                    break;
                }
            }
            //comeLine payout
            if (isComeBet == true && comeBet == 1) {
                //add comeLineAmount to playerWallet
                crapsPlayer.setPlayerWalletBalance(comeLineAmount);
                //remove comeLineAmount from houseWallet
                houseAmount -= comeLineAmount;
                inOut.println("Balance: " + crapsPlayer.getWallet());
                inOut.println("");
            } else if (isComeBet == true && comeBet == 2) {
                //remove comeLineAmount from playerWallet
                crapsPlayer.setPlayerWalletBalance(-(comeLineAmount));
                //add comeLineAmount to houseWallet
                houseAmount += comeLineAmount;
                inOut.println("Balance: " + crapsPlayer.getWallet());
                inOut.println("");
            } else if (isDontComeBet == true && dontComeBet == 1) {
                //add comeLineAmount to playerWallet
                crapsPlayer.setPlayerWalletBalance(comeLineAmount);
                //remove comeLineAmount from houseWallet
                houseAmount -= comeLineAmount;
                inOut.println("Balance: " + crapsPlayer.getWallet());
                inOut.println("");
            } else if (isDontComeBet == true && dontComeBet == 2) {
                //remove passLineAmount from playerWallet
                crapsPlayer.setPlayerWalletBalance(-(comeLineAmount));
                //add comeLineAmount to houseWallet
                houseAmount += comeLineAmount;
                inOut.println("Balance: " + crapsPlayer.getWallet());
                inOut.println("");
            }
        }
    }


//    private Integer getBetAmount(String prompt) {
//        inOut.println(prompt);
//        while (!scan.hasNextInt()) {
//            scan.next();
//            inOut.println("Enter bet amount");
//        }
//        return scan.nextInt();
//    }


    public void setInOut(Console inOut) {
        this.inOut = inOut;
    }

    public void displayGameState() {
        inOut.print("The ChoHan game state of the players:" + "\n");
        for (CrapsPlayer player : playerListCraps) {
            inOut.println("Name: " + player.getName() + " Wallet Balance: " + player.getWallet() + " NetGainLoss: " + player.getPlayerNetGainLoss());
        }
    }


    public void loadPlayer() {
    }

    public String getNextPlayerAction() {
        String userChoice = inOut.getStringInput("Enter 1 to Play another Craps Game" + "\n" +
                "Enter 2 to exit Craps go back to the High Roller Casino");
        return userChoice;
    }


    public Boolean playAgain() {
        return null;
    }


    public ArrayList<Integer> getPlayersNetGain() {
        ArrayList<Integer> playsrsNetGain = new ArrayList<Integer>();
        for (CrapsPlayer player : playerListCraps) {
            Integer store = player.getNetGainLossPlayerCraps();
            playsrsNetGain.add(store);
        }
        return playsrsNetGain;
    }

    public Integer getPlayerNetGain(){
        return crapsPlayer.getNetGainLossPlayerCraps();
    }

    public Integer getHouseCommisionAmt(Integer gameBetAmt) {
        Integer houseBalance = casinoObject.getHouseBalance();
        houseCommission += houseAmount;
        casinoObject.setHouseBalance(houseBalance);

        return houseCommission;
    }

    public Integer getHouseCommission(){
        return this.houseAmount;
    }

    public void exit() {
        System.exit(0);
    }


}

