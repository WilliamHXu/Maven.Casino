package io.zipcoder.casino.UtilitiesTests;

import io.zipcoder.casino.utilities.Card;
import io.zipcoder.casino.utilities.Hand;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class HandTest {

    @Test
    public void constructorTest(){
        // Given
        Hand hand = new Hand();
        // When
        ArrayList<Card> expected = new ArrayList<Card>();
        ArrayList<Card> actual = hand.getHandList();
        // Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void constructorTest2(){
        // Given
        ArrayList<Card> expected = new ArrayList<Card>();
        expected.add(new Card("D", 3));
        expected.add(new Card("D", 6));
        Hand hand = new Hand(expected);
        // When
        ArrayList<Card> actual = hand.getHandList();
        // Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void playCardFromHandTest(){
        // Given
        ArrayList<Card> expected = new ArrayList<Card>();
        Card card1 = new Card("D", 3);
        Card card2 = new Card("D", 6);
        expected.add(card1);
        expected.add(card2);
        Hand hand = new Hand(expected);
        // When
        Card actual = hand.playCardFromHand(card1);
        ArrayList<Card> actual2 = hand.getHandList();
        expected.remove(card1);
        // Then
        Assert.assertEquals(card1,actual);
        Assert.assertEquals(expected, actual2);
    }

    @Test
    public void playAllCardsTest(){
        // Given
        ArrayList<Card> expected = new ArrayList<Card>();
        expected.add(new Card("D", 3));
        expected.add(new Card("D", 6));
        Hand hand = new Hand(expected);
        // When
        Card card1 = new Card("D", 3);
        ArrayList<Card> expected1 = new ArrayList<Card>();
        expected1.add(card1);
        ArrayList<Card> actual = hand.playAllCards(3);
        Card card2 = new Card("D", 6);
        ArrayList<Card> expected2 = new ArrayList<Card>();
        ArrayList<Card> actual2 = hand.getHandList();

        // Then
        Assert.assertEquals(expected1, actual);
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void playAllCardsTest2(){
        // Given
        ArrayList<Card> expected = new ArrayList<Card>();
        Card card1 = new Card("D", 3);
        Card card2 = new Card("D", 6);
        expected.add(card1);
        expected.add(card2);
        Hand hand = new Hand(expected);
        // When
        ArrayList<Card> actual = hand.playAllCards("D");
        ArrayList<Card> expected2 = new ArrayList<Card>();
        ArrayList<Card> actual2 = hand.getHandList();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void addCardToHandTest() {
        // Given
        ArrayList<Card> expected = new ArrayList<Card>();
        Card card1 = new Card("D", 3);
        Card card2 = new Card("D", 6);
        Hand hand = new Hand(expected);
        expected.add(card1);
        expected.add(card2);
        // When
        hand.addCardToHand(card1);
        hand.addCardToHand(card2);
        // Then
        ArrayList<Card> actual = hand.getHandList();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void emptyHandTest() {
        // Given
        Card card1 = new Card("D", 3);
        Card card2 = new Card("D", 6);
        Hand hand = new Hand(new ArrayList<Card>());
        hand.addCardToHand(card1);
        hand.addCardToHand(card2);
        // When
        hand.emptyHand();
        ArrayList<Card> expected = new ArrayList<Card>();
        // Then
        ArrayList<Card> actual = hand.getHandList();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void contains1Test() {
        // Given
        ArrayList<Card> expected = new ArrayList<Card>();
        Card card1 = new Card("D", 3);
        Hand hand = new Hand(expected);
        hand.addCardToHand(card1);
        // When
        boolean test = hand.contains(card1);
        // Then
        Assert.assertTrue(test);
    }

    @Test
    public void contains2Test() {
        // Given
        ArrayList<Card> expected = new ArrayList<Card>();
        Card card1 = new Card("D", 3);
        Card card2 = new Card("D", 6);
        Hand hand = new Hand(expected);
        hand.addCardToHand(card1);
        // When
        boolean test = hand.contains(card2);
        // Then
        Assert.assertFalse(test);
    }

}
