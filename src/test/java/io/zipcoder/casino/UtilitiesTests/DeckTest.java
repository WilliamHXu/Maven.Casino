package io.zipcoder.casino.UtilitiesTests;

import io.zipcoder.casino.utilities.Card;
import io.zipcoder.casino.utilities.Deck;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class DeckTest {


    @Test
    public void cardDealFromTopTest(){
        // Given
        Deck deck = new Deck();
        Integer expectedValue = 1;
        // When
        Card actual = deck.cardDealFromTop();
        // Then
        String actualSuit = actual.getSuit();
        Integer actualValue = actual.getValue();
        Assert.assertEquals("D", actualSuit);
        Assert.assertEquals(expectedValue, actualValue);
    }


    @Test
    public void dealNFromTopTest(){
        // Given
        Deck deck = new Deck();
        Card card1 = new Card("D", 1);
        Card card2 = new Card("D", 2);
        Card card3 = new Card("D", 3);
        ArrayList<Card> cardArrayList = new ArrayList<Card>();
        cardArrayList.add(card1);
        cardArrayList.add(card2);
        cardArrayList.add(card3);
        // When
        ArrayList<Card> actualAL = deck.dealNFromTop(3);
        // Then
        Assert.assertEquals(cardArrayList.get(1), actualAL.get(1));
        Assert.assertEquals(cardArrayList.get(2), actualAL.get(2));
        Assert.assertEquals(cardArrayList.get(3), actualAL.get(3));
    }

    @Test
    public void getDeckTest(){
        // Given
        Card card1 = new Card("D", 1);
        Card card2 = new Card("D", 2);
        Card card3 = new Card("D", 3);
        ArrayList<Card> expected = new ArrayList<Card>();
        expected.add(card1);
        expected.add(card2);
        expected.add(card3);
        Deck deck = new Deck(expected);
        // When
        ArrayList<Card> actual = deck.getDeck();
        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shuffleTest(){
        // Given
        Deck deck = new Deck();
        Deck startingDeck = new Deck();
        // When
        deck.shuffle();
        // Then
        Assert.assertNotEquals(deck, startingDeck);
    }

    @Test
    public void refillTest(){
        // Given
        Deck deck = new Deck();
        Deck expected = new Deck();
        // When
        deck.shuffle();
        deck.refill();
        // Then
        Assert.assertEquals(expected.getDeck(), deck.getDeck());
    }




}
