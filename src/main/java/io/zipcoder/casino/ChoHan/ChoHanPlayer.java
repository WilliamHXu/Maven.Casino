package io.zipcoder.casino.ChoHan;

import io.zipcoder.casino.utilities.Player;

import java.util.*;

public class ChoHanPlayer {
    /** class objects */
    private Player playerObj;

    /** instance variables */
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private ArrayList<ChoHanPlayer> eligiblePlayerListCH = new ArrayList<ChoHanPlayer>();
    //private SortedMap<ChoHanPlayer, ArrayList<String>> gameListCH = new TreeMap();
    private Integer playerGuessCH;
    private String playerNameCH;
    private Integer walletBalanceCH;
    public Integer netGainLossPlayerCH;

    /**Nullary constructor*/
    public ChoHanPlayer()
    {
        this.playerNameCH = "";
        this.walletBalanceCH = 0;
        this.netGainLossPlayerCH = 0;
    }

    /** constructor taking in player list arraylist */
    public ChoHanPlayer(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    /** constructor instantiating instance variables */
    public ChoHanPlayer(String name, Integer walletBalance, Integer netGainLoss) {
        this.playerNameCH = name;
        this.walletBalanceCH = walletBalance;
        this.netGainLossPlayerCH = netGainLoss;
    }

    /** construct the ChoHan eligible player list with players that have
         enough wallet balance to play the game */
    public ArrayList<ChoHanPlayer> playerListChoHan(ArrayList<Player> playerList) {
      // ListIterator<Player> listIterator = playerList.listIterator();
        Player playerObj = playerList.get(0);
        int i =0;
        do {
            if (playerObj.getWallet() > 0) {
                eligiblePlayerListCH.add(new ChoHanPlayer(playerObj.getName(),
                        playerObj.getWallet(), playerObj.getPlayerNetGainLoss()));
            }
            i++;
            if(i<playerList.size())
            playerObj = playerList.get(i);
        }
        while (i<=playerList.size());

        return eligiblePlayerListCH;
    }

    /** check the player guess is for even or odd
     * @return integer guess*/
    public Integer getInputGuess(String playerGuessIn){

        if (playerGuessIn.equalsIgnoreCase("even"))
        {
            this.playerGuessCH = 2;
        }
        else if(playerGuessIn.equalsIgnoreCase("odd"))
        {
            this.playerGuessCH = 1;
        }

        return playerGuessCH;
    }

    public Integer getPlayerGuess(ChoHanPlayer player)
    {
       return player.playerGuessCH;
    }

    public Integer getEachPlayerBet(String playerName)
    {
        return 0;
    }

    /** return the list winner player names list*/
    public ArrayList<String> getWinnersCH (SortedMap<String,Integer> playerBetList, Integer throwOutput)
    {
        ArrayList<String> winnersList = new ArrayList<String>();

        for(Map.Entry<String, Integer> entry: playerBetList.entrySet())
        {
            if (throwOutput % 2 == 0 && entry.getValue() % 2 == 0)
            {
                winnersList.add(entry.getKey());
            }
        }

        return winnersList;
    }

    /** return the list loser player names list */
    public ArrayList<String> getLosersCH (SortedMap<String,Integer> playerBetList,Integer throwOutput)
    {
        ArrayList<String> losersList = new ArrayList<String>();
        for(Map.Entry<String, Integer> entry: playerBetList.entrySet())
        {
            if (throwOutput % 2 != 0 && entry.getValue() % 2 != 0)
            {
                losersList.add(entry.getKey());
            }
        }
        return losersList;
    }


    /** get method to return ChoHan eligible players */
    public ArrayList<ChoHanPlayer> getEligiblePlayerListCH() {
        return this.eligiblePlayerListCH;
    }

    /** get method to return ChoHan player wallet balance */
    public Integer getWalletBalanceChoHanPlayer() {
        return this.playerObj.getWallet();
    }

    /** set method to assign ChoHan player wallet balance */
    public void setWalletBalanceChoHanPlayer(ArrayList<String> winnerList, Integer gameBetAmt)
    {
        for (String winner : winnerList)
        {


        }

    }

//  public Integer updatePlayerWallet() {
//        return null;
//    }

    public String getPlayerNameCH() {
        return playerNameCH;
    }

    public void setPlayerNameCH(String playerNameCH) {
        this.playerNameCH = playerNameCH;
    }
}