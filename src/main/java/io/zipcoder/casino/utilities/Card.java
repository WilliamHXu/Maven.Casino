package io.zipcoder.casino.utilities;

public class Card implements Comparable<Card>{
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
        String valueString;
        if (this.value.equals(1)){
            valueString = "A";
        }
        else if (this.value.equals(11)){
            valueString = "J";
        }
        else if (this.value.equals(12)){
            valueString = "Q";
        }
        else if (this.value.equals(13)){
            valueString = "K";
        }
        else {
            valueString = value.toString();
        }
        return "" + valueString + suit;
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


    public int compareTo(Card o) {
        int compar1 = getValue().compareTo(o.getValue());


        return compar1;
    }
}
