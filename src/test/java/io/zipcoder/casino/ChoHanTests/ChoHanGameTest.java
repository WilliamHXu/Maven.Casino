package io.zipcoder.casino.ChoHanTests;
import io.zipcoder.casino.ChoHan.ChoHanGame;
import io.zipcoder.casino.ChoHan.ChoHanPlayer;
import io.zipcoder.casino.utilities.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.jvm.hotspot.oops.Array;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ChoHanGameTest {
    private ChoHanGame choHanGame;
    private ChoHanPlayer choHanPlayer;

    @Before
    public void initial() {
        ArrayList<Player> players = new ArrayList<Player>();
       ChoHanGame choHanGame = new ChoHanGame(players);
    }

    @Test
    public void constructorTest(){
        //Given
        ArrayList<Player> playerList = new ArrayList<Player>();
        Player player1 = new Player("John", 100);
        playerList.add(player1);
        //When
        choHanGame = new ChoHanGame(playerList);
        //Boolean actualName = choHanGame.getCHPlayersList();
        //Then
       // Assert.assertTrue(actualName);
    }


//    @Test
//    public void getDiceResultTest(){
//        //Given
//
//        //When
//        //Then
//    }


    @Test
    public void start() {
        //Given
        //When
        //Then
    }

}