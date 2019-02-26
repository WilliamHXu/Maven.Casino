package io.zipcoder.casino.utilities;

import io.zipcoder.casino.utilities.Card;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public boolean hasCard(Integer cardTocheck) {
        for (Card card : handList) {
            if (card.getValue().equals(cardTocheck))
                return true;

        }


        return false;
    }

    public ArrayList<Card> getCards(Integer getcard) {

        ArrayList<Card> retreiveCards = new ArrayList<Card>();

        for (Card card : handList) {
            if (card.getValue().equals(getcard))
                retreiveCards.add(card);

        }
        handList.removeAll(retreiveCards);

        return retreiveCards;
    }


    public void addtoHand(ArrayList<Card> cards){
        handList.addAll(cards);
    }

    /** verify the cards in the hand matches in the book*/

    public List<Card> bookEvalute(){
        Collections.sort(handList);
        ArrayList<Card> cardEvalu = new ArrayList<Card>();
        for (int i = 0; i < handList.size()-3; i++) {
            Card c1 = handList.get(i+0);
            Card c2 = handList.get(i+1);
            Card c3 = handList.get(i+2);
            Card c4 = handList.get(i+3);
            if(c1.getValue().equals(c2.getValue()) && c2.getValue().equals(c3.getValue()) && c3.getValue().equals(c4.getValue())) {
                cardEvalu.add(c1);
                cardEvalu.add(c2);
                cardEvalu.add(c3);
                cardEvalu.add(c4);
            }

        }
        return cardEvalu;
    }
}
