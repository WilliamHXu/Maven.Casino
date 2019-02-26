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
        this(new ArrayList<>());
    }

    // Methods

    /**
     * Returns a String for the current handList in the default format for each card
     */

    public String displayHand() {
        String handString = "";
        for (Card card : handList){
            String s = card.toString() + " ";;
            handString += s;
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


    @Override
    public String toString() {
        return "Hand{" +
                "handList=" + handList +
                '}';
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
        handList.add(card);
    }

    /**
     * Returns true if the card is in the hand. Else return false
     * @param card
     * @return
     */

    public Boolean contains(Card card) {
        return this.handList.contains(card);
    }


    public int getHandSum(){

        int handSum = 0;
        int numAces = 0;
        ArrayList<Card> handList = getHandList();
        // Calculate each card's contribution to handSum
        for (Card card : handList){
            // Check for Aces
            if(card.getValue().equals(1)){
                numAces++;
                handSum += 11;
            }
            // Check for Faces
            else if(card.getValue().equals(11) || card.getValue().equals(12) || card.getValue().equals(13)) {
                handSum += 10;
            }
            // Increment All Others
            else {
                handSum += card.getValue();
            }
        }
        // Deal with Aces
        while (handSum > 21 && numAces > 0){
            handSum -= 10;
            numAces--;
        }
        return handSum;
    }

}
