package io.zipcoder.casino.UtilitiesTests;

import io.zipcoder.casino.utilities.Player;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    // Getters

    @Test
    public void getNameTest(){
        // Given
        String expected = "Will";
        Player player = new Player(expected);
        // When
        String actual = player.getName();
        // Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getWalletTest(){
        // Given
        String name = "Will";
        Player player = new Player(name);
        Integer expected = 10000;
        // When
        Integer actual = player.getWallet();
        // Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getPlayerNetGainLossTest(){
        // Given
        String name = "Will";
        Player player = new Player(name);
        Integer expected = 0;
        // When
        Integer actual = player.getPlayerNetGainLoss();
        // Then
        Assert.assertEquals(expected,actual);
    }

    // Setters

    @Test
    public void setPlayerNameTest(){
        // Given
        String name = "Will";
        Player player = new Player(name);
        String newName = "Bobo";
        // When
        player.setPlayerName(newName);
        String actual = player.getName();
        // Then
        Assert.assertEquals(newName,actual);
    }

    @Test
    public void updateWalletTest(){
        // Given
        String name = "Will";
        Player player = new Player(name);
        // When
        player.updateWallet(500);
        Integer actual = player.getWallet();
        Integer expected = 10500;
        Integer actualGain = player.getPlayerNetGainLoss();
        Integer expectedGain = 500;
        // Then
        Assert.assertEquals(expected,actual);
        Assert.assertEquals(expectedGain, actualGain);
    }

    @Test
    public void goToATMTest(){
        // Given
        String name = "Will";
        Player player = new Player(name);
        // When
        player.goToATM(5000);
        Integer actual = player.getWallet();
        Integer expected = 15000;
        Integer actualGain = player.getPlayerNetGainLoss();
        Integer expectedGain = 0;
        // Then
        Assert.assertEquals(expected,actual);
        Assert.assertEquals(expectedGain, actualGain);
    }
}
