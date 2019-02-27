package io.zipcoder.casino.BlackJackTests;

import io.zipcoder.casino.BlackJack.BlackJackGame;
import io.zipcoder.casino.BlackJack.BlackJackPlayer;
import io.zipcoder.casino.utilities.Card;
import io.zipcoder.casino.utilities.Hand;
import io.zipcoder.casino.utilities.Player;
import org.junit.Assert;
import org.junit.Test;

public class BlackJackTests {



    @Test
    public void testGettingHandSum() {
        // Given
        Hand hand = new Hand();
        Card card1 = new Card("H",5);
        Card card2 = new Card("H", 5);

        // When
        Player player = new Player();
        BlackJackPlayer blackJackPlayer = new BlackJackPlayer(player);

        // Then
        int expected = 10;
        int actual = blackJackPlayer.getHandSum();

        System.out.println("Actual ::::: " + actual);

        Assert.assertEquals(expected,actual);

    }

    @Test
    public void printHandStateTest(){
        Player player = new Player("Charlie", 1000);
        BlackJackGame blackJackGame = new BlackJackGame(player);
        blackJackGame.dealInitialHand();
        blackJackGame.printHandState();
    }

    @Test
    public void testDealInitialHand() {
        Player player = new Player();
        Hand hand = new Hand();

        BlackJackPlayer blackJackPlayer = new BlackJackPlayer(player);
        BlackJackGame blackJackGame = new BlackJackGame(blackJackPlayer);

        blackJackGame.dealInitialHand();

        String showHand = blackJackPlayer.getHandString(true);

        System.out.println("SHOW HAND ::::" + showHand);


    }
    @Test
    public void testFor21(){


    }



}
