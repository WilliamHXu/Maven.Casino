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
    private ChoHanGame choHanGame;
    ArrayList<Player> PlayersList = new ArrayList<Player>();
    private ChoHanPlayer choHanPlayer;

    @Before
    public void initial() {
        Player player1 = new Player("Nirmala", 100);
        Player player2 = new Player("Will", 1000);
        Player player3 = new Player("Shaun", 0);
        Player player4 = new Player("Laxmi", 1500);
        Player player5 = new Player("Charlie", 2000);
        Player player6 = new Player("Sean", 500);
        PlayersList.add(player1);
        PlayersList.add(player2);
        PlayersList.add(player3);
        PlayersList.add(player4);
        PlayersList.add(player5);
        PlayersList.add(player6);
    }

    @Test
    public void getWalletBalanceChoHanPlayerTest() {
        //Given
        Player player1 = new Player("Nirmala", 100);
        PlayersList.add(player1);
        //When
        ChoHanPlayer choHanPlayer = new ChoHanPlayer(player1);
        Integer result = choHanPlayer.getWalletBalanceChoHanPlayer();
        //Them
        Assert.assertNotNull(result);
    }
}