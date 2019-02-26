package io.zipcoder.casino.ChoHanTests;
import io.zipcoder.casino.ChoHan.ChoHanGame;
import io.zipcoder.casino.ChoHan.ChoHanPlayer;
import io.zipcoder.casino.utilities.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ChoHanPlayerTest {
    private ChoHanPlayer choHanPlayer;
    @Before
    public void initial() {
        ArrayList<Player> players = new ArrayList<Player>();
        ChoHanGame choHanGame = new ChoHanGame(players);
    }

    @Test
    public void playerListChoHanTest()
    {
        //Given
        ArrayList<Player> playerList = new ArrayList<Player>();
        Player player1 = new Player("John", 100);
        Player player2 = new Player("Sal", 1000);
        Player player3 = new Player("Laxmi", 0);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        //When
        ArrayList<Player> expectedList = new ArrayList<Player>(playerList);
       // ArrayList<ChoHanPlayer> actualList = choHanPlayer.g;
        //playerList.remove(2);
        expectedList.remove(2);
        //Then
       // Assert.assertEquals(expectedList.size(),actualList.size());
    }
//
//    @Test
//    public void getplayerGuessTest(){
//        //Given
//        String PlayerGuessE = "Even";
//        String PlayerGuessO = "odd";
//        //When
//       // Integer actualPlayerGuessE = choHanPlayer.getPlayerGuess(PlayerGuessE);
//        //Integer actualPlayerGuessO = choHanPlayer.getPlayerGuess(PlayerGuessO);
//        Integer expectedPlayerGuessE = 2;
//        Integer expectedPlayerGuessO = 1;
//        //Then
//        Assert.assertEquals(expectedPlayerGuessE,actualPlayerGuessE);
//        Assert.assertEquals(expectedPlayerGuessO,actualPlayerGuessO);
//    }

    @Test
    public void updatePlayerWallet() {
    }

    @Test
    public void getHouseBalance() {
    }

    @Test
    public void getPlayerGuess() {
    }

    @Test
    public void getPlayerBets() {
    }
}