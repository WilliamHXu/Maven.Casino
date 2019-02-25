package io.zipcoder.casino;


import io.zipcoder.casino.Interfaces.Game;

public class Casino {
    public java.util.ArrayList playerList;
    public Integer keepPlaying;
    public Game currentGame;
    private Integer houseBalance;

    public static void main(String[] args) {
        // write your tests before you start fucking with this
    }

    void getStartingPlayers() {
    }

    void addPlayer() {
    }

    String getNextAction() {
        return "";
    }

    Integer getNumberOfPlayers() {
        return 0;
    }

    public Integer getHouseBalance()
    {
        return houseBalance;
    }

    public void setHouseBalance(Integer balance)
    {
        houseBalance = balance;
    }
}
