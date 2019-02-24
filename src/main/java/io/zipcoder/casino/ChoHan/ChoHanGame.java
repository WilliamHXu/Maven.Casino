package io.zipcoder.casino.ChoHan;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Interfaces.GamblingGame;
import io.zipcoder.casino.Interfaces.GamblingPlayer;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Dice;
import io.zipcoder.casino.utilities.Player;

import java.util.ArrayList;
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
    private SortedMap<String, ArrayList<String>> gameListCH = new TreeMap(); // store player Name,<guess,bet>
    public Double houseRate = 0.05;


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

//    public ArrayList<Player> getPlayerListChoHan() {
//        return playerListCHGame;
//    }

    /**
     * get the ChoHan eligible playerlist with enough wallet balance
     *
     * @return CHplayerList
     */
    public ArrayList<ChoHanPlayer> getPlayerListChoHan(ArrayList<Player> playerList) {

        return choHanPlayerObj.playerListChoHan(playerList);
    }

    public void start() {
        Integer numberOfDices, betInput, playerGuess, houseBalance, throwOutput, gameBetAmt = 0, houseCommission=0;
        String guessInput;
        // ArrayList<String> playerGuessBetList = new ArrayList<String>();
        SortedMap<String, Integer> playerBetList = new TreeMap<String, Integer>();
        SortedMap<String, Integer> playerGuessList = new TreeMap<String, Integer>();
        ArrayList<String> winnersList = new ArrayList<String>();
        ArrayList<String> losersList = new ArrayList<String>();

        /**get the ChoHan eligible playerlist with enough wallet balance*/
        playerListCH = getPlayerListChoHan(playerList);

        /** get the player guess and bet input for every CH player */
        for (ChoHanPlayer playerCH : playerListCH) {
            guessInput = inOut.getStringInput("Please enter guess: even or odd for CHoHan player: " + playerCH);
            betInput = inOut.getIntegerInput("Please enter bet amount on the guess for ChoHan player:  " + playerCH);

            playerGuess = choHanPlayerObj.getInputGuess(guessInput);
            playerBetList.put(playerCH.getPlayerNameCH(), betInput);
            playerGuessList.put(playerCH.getPlayerNameCH(), playerGuess);

            gameBetAmt += betInput;
            // gameListCH.put(playerCH.getPlayerNameCH(), playerGuessBetList);
        }

        /** get the house/dealer wallet balance and update the commission based on game bet money pot and the flat houseRate of 3% */
        houseBalance = casinoObj.getHouseBalance();
        houseCommission += (int) (gameBetAmt*houseRate);
        casinoObj.setHouseBalance(houseBalance+houseCommission);
        gameBetAmt -= houseCommission;

        /** get the number of dices to roll and instantiate the dice object */
        numberOfDices = inOut.getIntegerInput("Please enter number of Dices for the ChoHan Game: ");
        Dice diceObj = new Dice(numberOfDices);

        /** get the dice throw output and display the dice result */
        throwOutput = diceObj.throwAndSum();
        if (throwOutput % 2 == 0)
        {
            inOut.getStringInput("The dice throw result for this game is Cho: " + throwOutput);
        }
        else
        {
            inOut.getStringInput("The dice throw result for this game is Han: " + throwOutput);
        }

        /** pick the winners/losers for the game */
        winnersList = choHanPlayerObj.getWinnersCH(playerBetList, throwOutput);
        losersList = choHanPlayerObj.getLosersCH(playerBetList, throwOutput);

        /** update winner players wallets */

            choHanPlayerObj.setWalletBalanceChoHanPlayer(winnersList,gameBetAmt);

        // (each winner bet / sum the winner players bet) * gamebetAmt = each winning player winning amount


        /** update the house wallet */

    }


    public Integer askForBet(GamblingPlayer gamblingPlayer) {

        return null;
    }


    public Integer getDiceResult(Integer numberOfDices) {
        Dice dice = new Dice(numberOfDices);
        return 0;
    }

    public void loadPlayer() {

    }

    public String getNextPlayerAction() {
        return null;
    }

    public void displayGameState() {

    }

    public Boolean playAgain() {
        return null;
    }

    public void exit() {
        System.exit(0);

    }

}
