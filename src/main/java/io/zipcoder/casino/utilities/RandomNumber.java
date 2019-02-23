package io.zipcoder.casino.utilities;

public class RandomNumber {

    /**
     * Returns a random INteger between 1 and maxValue inclusive
     * @param maxValue
     * @return
     */
    public static Integer getRandomNumber(Integer maxValue) {
        return (int)(Math.random()*maxValue) + 1 ;
    }
}
