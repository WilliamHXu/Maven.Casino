package io.zipcoder.casino.ChoHan;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Interfaces.GamblingGame;
import io.zipcoder.casino.Interfaces.GamblingPlayer;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Dice;
import io.zipcoder.casino.utilities.Player;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ChoHanGame implements Game, GamblingGame {

    /**
     * class objects
     */
    private ChoHanPlayer choHanPlayerObj;
    private Console inOut = new Console(System.in, System.out);
    private Casino casinoObj = new Casino();

    /**
     * Instance variables
     */
    private ArrayList<Player> playerList = new ArrayList<Player>(); //playerList from Player class
    private ArrayList<ChoHanPlayer> playerListCH = new ArrayList<ChoHanPlayer>(); //ChoHan player list
    private SortedMap<String, Integer> playerBetList = new TreeMap<String, Integer>();
    private SortedMap<String, Integer> playerGuessList = new TreeMap<String, Integer>();
    public Double houseRate = 0.05;

//  private SortedMap<String, ArrayList<String>> gameListCH = new TreeMap(); // store player Name,<guess,bet>

    /**
     * constructor with player list
     */
    public ChoHanGame(ArrayList<Player> playerList) {
        this.playerList = playerList;
        choHanPlayerObj = new ChoHanPlayer(playerList);
    }

    /**
     * default constructor
     */
    public ChoHanGame() {
    }

    /**
     * get the ChoHan eligible playerlist with enough wallet balance
     *
     * @return cHPlayerList
     */
    public ArrayList<ChoHanPlayer> getPlayerListChoHan(ArrayList<Player> playerList) {

        return choHanPlayerObj.playerListChoHan(playerList);
    }

    public void start() {
        Integer numberOfDices, betInput, playerGuess, houseBalance, throwOutput;
        Integer gameBetAmt = 0, houseCommission = 0, winnersBetSum = 0;
        String guessInput;
        // ArrayList<String> playerGuessBetList = new ArrayList<String>();

        ArrayList<String> winnersList = new ArrayList();
        ArrayList<String> losersList = new ArrayList();

        /**get the ChoHan eligible playerlist with enough wallet balance*/
        playerListCH = getPlayerListChoHan(playerList);
        inOut.println("Below are the list of Players eligible to bet and play ChoHan: ");
        for(ChoHanPlayer player: playerListCH)
        { inOut.println("Player Name: "+ player.getPlayerNameCH() +
                "Player wallet Balance: " + player.getWalletBalanceChoHanPlayer());}


        /** get the player guess and bet input for every CH player */
        for (ChoHanPlayer playerCH : playerListCH) {
            guessInput = inOut.getStringInput("Please enter guess: even or odd for CHoHan player: " + playerCH);
            betInput = inOut.getIntegerInput("Please enter bet amount on the guess for ChoHan player:  " + playerCH);

            playerGuess = choHanPlayerObj.getInputGuess(guessInput);
            playerBetList.put(playerCH.getPlayerNameCH(), betInput);
            playerGuessList.put(playerCH.getPlayerNameCH(), playerGuess);

            gameBetAmt += betInput;
        }

        /** get the house/dealer wallet balance and update the commission based on game bet money pot and
         * the flat houseRate defined at game level */
        houseBalance = casinoObj.getHouseBalance();
        houseCommission += (int) (gameBetAmt * houseRate);
        casinoObj.setHouseBalance(houseBalance + houseCommission);
        gameBetAmt -= houseCommission;

        /** get the number of dices to roll and instantiate the dice object */
        numberOfDices = inOut.getIntegerInput("Please enter number of Dices for the ChoHan Game: ");
        throwOutput = getDiceResult(numberOfDices);

        /** pick the winners/losers for the game based on dice throw result and players guess */
        winnersList = choHanPlayerObj.getWinnersCH(playerBetList, throwOutput);
        winnersBetSum = getWinnersBetSum(winnersList, playerBetList);
        losersList = choHanPlayerObj.getLosersCH(playerBetList, throwOutput);

        /** update winner players wallets
         * each winning player winning amount = (each winner bet amount/sum of winners bets)* bet amount for this game */
        for (String winner : winnersList) {
            for (Map.Entry<String, Integer> entry : playerGuessList.entrySet()) {
                if (winner.equalsIgnoreCase(entry.getKey())) {
                   int winnerWalletAddition = (choHanPlayerObj.getEachPlayerBet(winner) / winnersBetSum) * gameBetAmt;
                    choHanPlayerObj.setPlayerWalletBalance(winner, winnerWalletAddition);
                    choHanPlayerObj.setNetGainLossPlayerCH(winnerWalletAddition);
                }
            }
        }

        /** update losing players wallets */
        for (String loser : losersList) {
            for (Map.Entry<String, Integer> entry : playerGuessList.entrySet()) {
                if (loser.equalsIgnoreCase(entry.getKey())) {
                    int loserWalletReduction= choHanPlayerObj.getEachPlayerBet(loser);
                    loserWalletReduction *= -1;
                    choHanPlayerObj.setPlayerWalletBalance(loser, loserWalletReduction);
                    choHanPlayerObj.setNetGainLossPlayerCH(loserWalletReduction);
                }
            }
        }

        displayGameState();


        Integer userChoice = Integer.valueOf(getNextPlayerAction());
        if (userChoice == 1)
        { start();}

    }

    public void displayGameState() {
        inOut.print("The ChoHan game state of the players:");
     //   for(playerListCH)
      //  inOut.println();

    }


    /**
     * total amount of money to be shared with winners from the money pot
     *
     * @return winnersTotalBetAmount
     */
    private Integer getWinnersBetSum(ArrayList<String> winnersList, SortedMap<String, Integer> playerBets) {
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

    /** get the dice throw output and display the dice result to players
     * Cho - even outcome
     * Han - odd outcome
     @return dicethrowoutput */
    public Integer getDiceResult(Integer numberOfDices) {
        Dice diceObj = new Dice(numberOfDices);
        Integer throwOutput = diceObj.throwAndSum();
        if (throwOutput % 2 == 0) {
            inOut.getStringInput("The dice throw result for this game is Cho: " + throwOutput);
        } else {
            inOut.getStringInput("The dice throw result for this game is Han: " + throwOutput);
        }
        return throwOutput;
    }


    public SortedMap<String, Integer> getPlayerBetList() {
        return playerBetList;
    }

    public SortedMap<String, Integer> getPlayerGuessList() {
        return playerGuessList;
    }

    public Integer askForBet(GamblingPlayer gamblingPlayer) {

        return null;
    }

    public void loadPlayer() {

    }

    public String getNextPlayerAction() {
       String userChoice = inOut.getStringInput("Enter 1 to Play another ChoHan Game" +"\n" +
                "Enter 2 to exit ChoHan go back to the High Roller Casino");
        return userChoice;
    }

    public Boolean playAgain() {
        return null;
    }

    public void exit() {
        System.exit(0);

    }

}
