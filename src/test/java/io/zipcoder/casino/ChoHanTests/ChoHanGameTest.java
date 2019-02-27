package io.zipcoder.casino.ChoHanTests;

import com.sun.media.jfxmedia.events.PlayerStateEvent;
import io.zipcoder.casino.ChoHan.ChoHanGame;
import io.zipcoder.casino.ChoHan.ChoHanPlayer;
import io.zipcoder.casino.utilities.Player;
import io.zipcoder.casino.utilities.Dice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.*;

import static org.junit.Assert.*;

public class ChoHanGameTest {
    private ChoHanGame choHanGame;
    ArrayList<Player> PlayersList = new ArrayList<Player>();

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
    public void constructorTest() {
        //Given
        ArrayList<Player> PlayersList = new ArrayList<Player>();
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
        //When
        ArrayList<Player> expectedPlayers = new ArrayList<Player>();
        expectedPlayers.addAll(PlayersList);
        expectedPlayers.remove(player3);
        choHanGame = new ChoHanGame(PlayersList);
        ArrayList<ChoHanPlayer> actualPlayers = choHanGame.getCHPlayersList();
        //Then
        Assert.assertTrue(!actualPlayers.contains(player3));
        // Assert.assertEquals(actualStr,expectedStr);
    }

//    @Test
//    public void getGameBetTest() {
//        //Given
//        //   ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        // Console console = getConsoleWithBufferedInputandOutput("String input", baos);
//        // Casino casino = new
//        choHanGame = new ChoHanGame(PlayersList);
//        SortedMap<String, Integer> actualBetList = new TreeMap<String, Integer>();
//        SortedMap<String, String> actualGuessList = new TreeMap<String, String>();
//        actualBetList.put("Nirmala", 20);
//        actualBetList.put("Will", 30);
//        actualBetList.put("Laxmi", 40);
//        actualBetList.put("Charlie", 50);
//        actualBetList.put("Sean", 10);
//        actualGuessList.put("Nirmala", "even");
//        actualGuessList.put("Will", "odd");
//        actualGuessList.put("Laxmi", "even");
//        actualGuessList.put("Charlie", "even");
//        actualGuessList.put("Sean", "odd");
//        //When
//        Integer expectedBetAmt = 150;
//        Integer actualBetAmt = choHanGame.getGameBetAmt(choHanGame.getCHPlayersList());
//        //Then
//        Assert.assertEquals(expectedBetAmt, actualBetAmt);
//    }

    @Test
    public void throwSumTest() {
        //Given
        Integer noOfDice = 4;
        choHanGame = new ChoHanGame(PlayersList);
        //When
        Integer actualRandNum = choHanGame.getDiceResult(noOfDice);
        //Then
        Assert.assertNotNull(actualRandNum);
    }

    @Test
    public void getHCommissionTest() {
        //Given
        choHanGame = new ChoHanGame(PlayersList);
        Integer beforeHCommission = choHanGame.getHouseCommission();
        //When
        Integer afterHCommission = choHanGame.getHouseCommisionAmt(500);
        //Then
        Boolean result = beforeHCommission < afterHCommission;
        Assert.assertTrue(result);
    }

    @Test
    public void getWinnersCHTest() {
        // Given
        SortedMap<String, String> actualGuessList = new TreeMap<String, String>();
        ArrayList<String> expectedWinners = new ArrayList();
        ArrayList<String> actualWinners = new ArrayList();
        //When
        choHanGame = new ChoHanGame(PlayersList);
        actualGuessList.put("Nirmala", "even");
        actualGuessList.put("Will", "odd");
        actualGuessList.put("Laxmi", "even");
        actualGuessList.put("Charlie", "even");
        actualGuessList.put("Sean", "odd");
        expectedWinners.add("Nirmala");
        expectedWinners.add("Laxmi");
        expectedWinners.add("Charlie");
        actualWinners = choHanGame.getWinnersCH(actualGuessList, 10);
        Collections.sort(expectedWinners);
        Collections.sort(actualWinners);
        //Then
        Assert.assertEquals(expectedWinners, actualWinners);

    }

    @Test
    public void getLosersCHTest() {
        // Given
        SortedMap<String, String> actualGuessList = new TreeMap<String, String>();
        ArrayList<String> expectedLossers = new ArrayList();
        ArrayList<String> actualWinners = new ArrayList();
        //When
        choHanGame = new ChoHanGame(PlayersList);
        actualGuessList.put("Nirmala", "even");
        actualGuessList.put("Will", "odd");
        actualGuessList.put("Laxmi", "even");
        actualGuessList.put("Charlie", "even");
        actualGuessList.put("Sean", "odd");
        expectedLossers.add("Will");
        expectedLossers.add("Sean");
        actualWinners = choHanGame.getLosersCH(actualGuessList, 10);
        Collections.sort(expectedLossers);
        Collections.sort(actualWinners);
        //Then
        Assert.assertEquals(expectedLossers, actualWinners);

    }

    @Test
    public void getWinnersBetSumTest() {
        //Given
        ArrayList<String> actualWinnersList = new ArrayList<String>();
        //When
        choHanGame = new ChoHanGame(PlayersList);
        actualWinnersList.add("Nirmala");
        actualWinnersList.add("Will");
        actualWinnersList.add("Laxmi");
        actualWinnersList.add("Charlie");
        actualWinnersList.add("Sean");

        SortedMap<String, Integer> actualBetList = new TreeMap<String, Integer>();
        actualBetList.put("Nirmala", 20);
        actualBetList.put("Will", 40);
        actualBetList.put("Laxmi", 50);
        actualBetList.put("Charlie", 30);
        actualBetList.put("Sean", 50);
        Integer expectedWinTotBet = 190;
        Integer actualWinTotBet = choHanGame.getWinnersBetSum(actualWinnersList, actualBetList);
        //Then
        Assert.assertEquals(expectedWinTotBet, actualWinTotBet);
    }

    @Test
    public void setPlayerWalletBalanceTest(){
        //Given
        SortedMap<String, String> actualGuessList = new TreeMap<String, String>();
        ArrayList<String> actualWinners = new ArrayList();
        ArrayList<String> actualWinnersList = new ArrayList<String>();
        //When
        choHanGame = new ChoHanGame(PlayersList);
        actualWinnersList.add("Nirmala");
        actualWinnersList.add("Will");
        actualWinnersList.add("Laxmi");
        actualWinnersList.add("Charlie");
        actualWinnersList.add("Sean");

        SortedMap<String, Integer> actualBetList = new TreeMap<String, Integer>();
        actualBetList.put("Nirmala", 20);
        actualBetList.put("Will", 40);
        actualBetList.put("Laxmi", 50);
        actualBetList.put("Charlie", 30);
        actualBetList.put("Sean", 50);
        Integer expectedWinTotBet = 190;

        actualGuessList.put("Nirmala", "even");
        actualGuessList.put("Will", "odd");
        actualGuessList.put("Laxmi", "even");
        actualGuessList.put("Charlie", "even");
        actualGuessList.put("Sean", "odd");
        actualWinners = choHanGame.getLosersCH(actualGuessList, 10);

    }

}