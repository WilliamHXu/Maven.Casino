package io.zipcoder.casino.utilities;

public class Card {
    // Instance Variables
    private String suit;
    private Integer value;

    // Constructors

    public Card(String suit, Integer value) {
        this.suit = suit;
        this.value = value;
    }

    // Methods

    /**
     * Returns the Card as a String
     * @return
     */

    public String toString() {
        return "" + suit + value;
    }

    /**
     * Returns the card's Value
     * @return
     */

    public Integer getValue() {
        return this.value;
    }

    /**
     * Returns the card's Suit
     * @return
     */

    public String getSuit() {
        return this.suit;
    }
}
