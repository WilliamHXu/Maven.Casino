package io.zipcoder.casino.UtilitiesTests;

import io.zipcoder.casino.utilities.Card;
import org.junit.Assert;
import org.junit.Test;

public class CardTest {

    @Test
    public void constructorTest(){
        // Given
        String suit = "H";
        Integer value = 5;

        // When
        Card card = new Card(suit, value);
        String actualSuit = card.getSuit();
        Integer actualValue = card.getValue();

        // Then
        Assert.assertEquals(suit, actualSuit);
        Assert.assertEquals(value,actualValue);
    }

}
