package io.zipcoder.casino.GoFish;

import io.zipcoder.casino.utilities.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GoFishGameTest {


@Test
    public void testSetPlayerBookTrue(){

    int expectedCount = 1;
 //   ArrayList<Player> players = new ArrayList<>();


  GoFishGame goFishGame = new GoFishGame();
  goFishGame.setPlayerBook(1);

    int actualBookCount = goFishGame.getPlayerBook();

    Assert.assertEquals(expectedCount, actualBookCount);

}

@Test

    public void testSetPlayerBookFalse(){

    int expBookCount =1;
    GoFishGame goFishGame = new GoFishGame();
    goFishGame.setPlayerBook(18);
    int actBookCount = goFishGame.getPlayerBook();
    Assert.assertNotEquals(expBookCount, actBookCount);

}

@Test
    public void testSetOpponentBookTrue(){}

@Test
    public void testSetOpponentBookFalse(){}

@Test
    public void testSetPlayerTotalCards(){

    int expTotalCards = 7;
    GoFishGame goFishGame = new GoFishGame();
    goFishGame.setPlayerTotalCards(7);
    int actTotalCards = goFishGame.getPlayerTotalCards();
    Assert.assertEquals(expTotalCards, actTotalCards);

}

@Test
    public  void testSetOpponentTotalCards(){
    int expTotalCards = 7;
    GoFishGame goFishGame = new GoFishGame();
    goFishGame.setOpponentTotalCards(7);
    int actTotalCards = goFishGame.getOpponentTotalCards();
    Assert.assertEquals(expTotalCards, actTotalCards);

}


@Test
    public void testIncrementPlayerBookOnce(){
    int expTotalBooks = 2;
    GoFishGame goFishGame = new GoFishGame();
    goFishGame.setPlayerBook(1);
    goFishGame.incrementPlayersBook();
    int actCardCount = goFishGame.getPlayerBook();
    Assert.assertEquals(expTotalBooks, actCardCount);
}

@Test
    public void testIncrementPlayersBook10Times(){

    int expTotalBooks = 10;
    GoFishGame goFishGame = new GoFishGame();
    for (int i = 0; i < 10; i++) {
        goFishGame.incrementPlayersBook();
    }
    int actCardCount = goFishGame.getPlayerBook();
    Assert.assertEquals(expTotalBooks, actCardCount);
    }



}