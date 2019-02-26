package io.zipcoder.casino.ChoHan;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Dice;
import io.zipcoder.casino.utilities.Player;

import java.util.*;

public class ChoHanGame{

    /**
     * Instance variables
     */
    private Console inOut = new Console(System.in, System.out);
    private Casino casinoObj = new Casino();

    private ArrayList<Player> playerList = new ArrayList<Player>(); //playerList from Player class
    private ArrayList<ChoHanPlayer> playerListCH = new ArrayList<ChoHanPlayer>(); //ChoHan player list
    ChoHanPlayer choHanPlayer;
    private SortedMap<String, Integer> playerBetList = new TreeMap<String, Integer>();
    private SortedMap<String, String> playerGuessList = new TreeMap<String, String>();
    private Integer userChoice = 1;
    boolean action = true;
    private Double houseRate = 0.05;
    Integer houseCommission = 0;


    public ChoHanGame(ArrayList<Player> playerList) {
        this.playerList = playerList;
        for (Player player : playerList) {
            choHanPlayer = new ChoHanPlayer(player);
            if (choHanPlayer.getWallet() > 0) {
                playerListCH.add(choHanPlayer);
            }

        }
    }

    /**
     * Cho Han game start
     */
    public void start() {
        while (action) {
            /** local variables */
            Integer gameBetAmt = 0, numberOfDices, throwOutput;
            Integer winnersBetSum = 0;
            ArrayList<String> winnersList = new ArrayList();
            ArrayList<String> losersList = new ArrayList();

            /** Display players state before game start*/
            inOut.println("Below are the list of Players eligible to bet and play ChoHan: ");
            for (ChoHanPlayer player : playerListCH) {
                inOut.println("Player Name: " + player.getName() +
                        ", wallet Balance: " + player.getWallet());
            }
            gameBetAmt = getGameBetAmt(playerListCH);


            /** get the house/dealer wallet balance and update the commission based on game bet money pot and
             * the flat houseRate defined at game level */
            gameBetAmt -= getHouseCommisionAmt(gameBetAmt);


            /** get the number of dices to roll and instantiate the dice object */
            numberOfDices = inOut.getIntegerInput("Please enter number of Dices for the ChoHan Game: ");
            throwOutput = getDiceResult(numberOfDices);


            /** pick the winners/losers for the game based on dice throw result and players guess */
            winnersList = getWinnersCH(getPlayerGuessList(), throwOutput);
            winnersBetSum = getWinnersBetSum(winnersList, getPlayerBetList());
            losersList = getLosersCH(getPlayerGuessList(), throwOutput);

//            /** calculate and update each winner players wallets
//             * each winning player winning amount = (each winner bet amount/sum of winners bets)* bet amount for this game */
//            for (int i = 0; i < winnersList.size(); i++) {
//                for (Map.Entry<String, Integer> entry : getPlayerBetList().entrySet()) {
//                    if (winnersList.get(i).equalsIgnoreCase(entry.getKey())) {
//                        int winnerBetAmt = entry.getValue();
//                        double winPercent = winnerBetAmt / (double) winnersBetSum;
//                        int winnerWalletAddition = (int) (winPercent * gameBetAmt);
//                        this.choHanPlayer.setPlayerWalletBalance(entry.getKey(), winnerWalletAddition);
//                        this.choHanPlayer.setNetGainLossPlayerCH(winnerWalletAddition);
//                    }
//                }
//            }


            /** calculate and update each winner players wallets
             * each winning player winning amount = (each winner bet amount/sum of winners bets)* bet amount for this game */
            for (int i = 0; i < winnersList.size(); i++) {
                for (Map.Entry<String, Integer> entry : getPlayerBetList().entrySet()) {
                    if (winnersList.get(i).equalsIgnoreCase(entry.getKey())) {
                        for (ChoHanPlayer choHanPlayer : playerListCH) {
                            if (choHanPlayer.getName().equals(winnersList.get(i))) {
                                int winnerBetAmt = entry.getValue();
                                double winPercent = winnerBetAmt / (double) winnersBetSum;
                                int winnerWalletAddition = (int) (winPercent * gameBetAmt);
                                choHanPlayer.setPlayerWalletBalance(winnerWalletAddition);
                                //choHanPlayer.setNetGainLossPlayerCH(winnerWalletAddition);

                            }
                        }
                    }
                }
            }

            /** update losing players wallets */
            for (int i = 0; i < losersList.size(); i++) {
                for (Map.Entry<String, Integer> entry : getPlayerBetList().entrySet()) {
                    if (losersList.get(i).equalsIgnoreCase(entry.getKey())) {
                        for (ChoHanPlayer choHanPlayer : playerListCH) {
                            if (choHanPlayer.getName().equals(losersList.get(i))) {
                                int loserWalletReduction = entry.getValue();
                                loserWalletReduction *= -1;
                                choHanPlayer.setPlayerWalletBalance(loserWalletReduction);
                            //    choHanPlayer.setNetGainLossPlayerCH(loserWalletReduction);
                            }
                        }
                    }
                }
            }
            inOut.println("This ChoHan Game Winners: " + winnersList);
            inOut.println("This ChoHan Game Losers: " + losersList);

            displayGameState();
            if (action) {
                action = getNextPlayerAction(userChoice);
            } else
                break;
        }

    }

    /**
     * display player game state for all players at end of each game
     */
    public void displayGameState() {
        inOut.print("The ChoHan game state of the players:" + "\n");
        for (ChoHanPlayer player : playerListCH) {
            inOut.println("Name: " + player.getName() + " Wallet Balance: " + player.getWallet() + " NetGainLoss: " + player.getPlayerNetGainLoss());
        }
    }

    /**
     * get players next action
     */
    public Boolean getNextPlayerAction(Integer userChoice) {
        boolean ret = true;
        userChoice = inOut.getIntegerInput("Enter 1 to Play another ChoHan Game" + "\n" +
                "Enter 2 to exit ChoHan and go back to the High Roller Casino");
        if (userChoice == 1) {
            ret = true;
        } else if (userChoice == 2) {
            ret = false;
        }
        return ret;
    }

    /** calculate the house commission
     * @return houseCommissionAmount */
    private Integer getHouseCommisionAmt(Integer gameBetAmt) {
        Integer houseBalance = casinoObj.getHouseBalance();
        houseCommission += (int) (gameBetAmt * houseRate);
        casinoObj.setHouseBalance(houseBalance + houseCommission);

        return houseCommission;
    }

    /**
     * get the player guess of even or odd and get player bets
     *
     * @return integer gameBetAmount
     */
    public Integer getGameBetAmt(ArrayList<ChoHanPlayer> playerListCH) {
        String guessInput;
        Integer betInput, gameBetAmt = 0;

        for (ChoHanPlayer player : playerListCH) {
            guessInput = inOut.getStringInput("Please enter guess: even or odd for CHoHan player: " + player.getName());
            betInput = inOut.getIntegerInput("Please enter bet amount on the guess for ChoHan player:  " + player.getName());

            playerBetList.put(player.getName(), betInput);
            playerGuessList.put(player.getName(), guessInput);
            gameBetAmt += betInput;
        }
        return gameBetAmt;
    }

    public SortedMap<String, Integer> getPlayerBetList() {
        return playerBetList;
    }

    public SortedMap<String, String> getPlayerGuessList() {
        return playerGuessList;
    }

    /**
     * get the ChoHan eligible playerlist with enough wallet balance
     *
     * @return cHPlayerList
     */
    public ArrayList<ChoHanPlayer> getCHPlayersList() {
        return this.playerListCH;
    }

    /**
     * get the dice throw output and display the dice result to players
     * Cho - even outcome
     * Han - odd outcome
     *
     * @return dicethrowoutput
     */
    public Integer getDiceResult(Integer numberOfDices) {
        Dice diceObj = new Dice(numberOfDices);
        Integer throwOutput = diceObj.throwAndSum();
        if (throwOutput % 2 == 0) {
            inOut.println("The dice throw result for this game is Cho: " + throwOutput);
        } else {
            inOut.println("The dice throw result for this game is Han: " + throwOutput);
        }
        return throwOutput;
    }

    /**
     * return the list winner player names list
     */
    public ArrayList<String> getWinnersCH(SortedMap<String, String> playerGuessList, Integer throwOutput) {
        ArrayList<String> winnersList = new ArrayList<String>();

        for (Map.Entry<String, String> entry : playerGuessList.entrySet()) {
            if ((throwOutput % 2 == 0 && entry.getValue().equalsIgnoreCase("even") ||
                    (throwOutput % 2 != 0 && entry.getValue().equalsIgnoreCase("odd")))) {
                winnersList.add(entry.getKey());
            }
        }
        return winnersList;
    }

    /**
     * return the list loser player names list
     */
    public ArrayList<String> getLosersCH(SortedMap<String, String> playerGuessList, Integer throwOutput) {
        ArrayList<String> losersList = new ArrayList<String>();
        for (Map.Entry<String, String> entry : playerGuessList.entrySet()) {
            if ((throwOutput % 2 == 0 && entry.getValue().equalsIgnoreCase("odd") ||
                    (throwOutput % 2 != 0 && entry.getValue().equalsIgnoreCase("even")))) {
                losersList.add(entry.getKey());
            }
        }
        return losersList;
    }

    /**
     * total amount of money to be shared with winners from the money pot
     *
     * @return winnersTotalBetAmount
     */
    public Integer getWinnersBetSum(ArrayList<String> winnersList, SortedMap<String, Integer> playerBets) {
        Integer winnersTotalBet = 0;
        for (String winner : winnersList) {
            for (Map.Entry<String, Integer> entry : playerBets.entrySet()) {
                if (winner.equalsIgnoreCase(entry.getKey())) {
                    winnersTotalBet += entry.getValue();
                }
            }
        }
        return winnersTotalBet;
    }

    public ArrayList<Integer> getPlayerNetGain(){
        ArrayList<Integer> playsrsNetGain = new ArrayList<Integer>();
        for (ChoHanPlayer player: playerListCH) {
            Integer store = player.getNetGainLossPlayerCH();
            playsrsNetGain.add(store);
        }
        return playsrsNetGain;
    }

    public Integer getHouseCommission(){
        return this.houseCommission;
    }

}


