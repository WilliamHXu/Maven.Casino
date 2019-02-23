package io.zipcoder.casino.utilities;

import io.zipcoder.casino.utilities.Card;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Hand {
    // Instance Variables
    protected ArrayList<Card> handList;

    // Constructors

    public Hand(ArrayList<Card> handList) {
        this.handList = handList;
    }

    public Hand(){
        this(new ArrayList<Card>());
    }

    // Methods

    /**
     * Returns a String for the current handList in the default format for each card
     */

    public String displayHand() {
        String handString = "";
        for (Card card : handList){
            handString += card.toString() + " ";
        }
        return handString;
    }

    /**
     * Gets the handList
     * @return
     */

    public ArrayList<Card> getHandList() {
        return handList;
    }

    /**
     * removes and returns the card from the handList if it exists, else return null
     * @param card
     * @return
     */

    public Card playCardFromHand(Card card) {
        if(handList.remove(card)){
            return card;
        }
        else {
            return null;
        }
    }

    /**
     * Removes all cards on a certain suit from the hand and returns in an ArrayList
     * @param suit
     * @return
     */

    public ArrayList<Card> playAllCards(String suit) {
        ArrayList<Card> playedCards = new ArrayList<Card>();
        for(Card card : handList){
            if(card.getSuit().equals(suit)){
                playCardFromHand(card);
                playedCards.add(card);
            }
        }
        return playedCards;
    }

    /**
     * Removes all cards on a certain value from the hand and returns in an ArrayList
     * @param value
     * @return
     */

    public ArrayList<Card> playAllCards(Integer value) {
        ArrayList<Card> playedCards = new ArrayList<Card>();
        for(Card card : handList){
            if(card.getValue().equals(value)){
                playCardFromHand(card);
                playedCards.add(card);
            }
        }
        return playedCards;
    }

    /**
     * Sets the handList to an empty ArrayList
     */

    public void emptyHand() {
        this.handList = new ArrayList<Card>();
    }

    /**
     * Adds the card to the handList
     * @param card
     */

    public void addCardToHand(Card card) {
        this.handList.add(card);
    }

    /**
     * Returns true if the card is in the hand. Else return false
     * @param card
     * @return
     */

    public Boolean contains(Card card) {
        return this.handList.contains(card);
    }
}
