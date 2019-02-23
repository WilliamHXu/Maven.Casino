package io.zipcoder.casino.UtilitiesTests;

import io.zipcoder.casino.utilities.Dice;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class DiceTest {

    @Test
    public void constructorTest(){
        // Given
        Dice dice = new Dice(5);
        // When
        Integer expected1 = 5;
        Integer actual1 = dice.getNumberOfDice();
        Integer[] expected2 = {1, 1, 1, 1, 1};
        Integer[] actual2 = dice.getDiceArray();
        // Then
        Assert.assertEquals(expected1, actual1);
        Assert.assertArrayEquals(expected2, actual2);
    }

    @Test
    public void throwAndSumTest(){
        // Given
        Dice dice = new Dice(5);
        // When
        Integer actual1 = dice.throwAndSum();
        boolean check1 = actual1 > 4 && actual1 < 31;
        Integer[] expectedCheck = {1, 1, 1, 1, 1};
        Integer[] actual2 = dice.getDiceArray();
        boolean check2 = Arrays.equals(expectedCheck, actual2);
        // Then
        Assert.assertTrue(check1);
        Assert.assertFalse(check2);
    }

}
