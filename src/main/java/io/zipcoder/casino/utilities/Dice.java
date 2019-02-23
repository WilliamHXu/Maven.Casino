package io.zipcoder.casino.utilities;


public class Dice {
    // Instance Variables

    private Integer numberOfDice;
    private Integer[] diceArray;

    // Constructors

    public Dice(Integer numberOfDice){
        this.numberOfDice = numberOfDice;
        this.diceArray = new Integer[numberOfDice];
        for(int i = 0; i < numberOfDice; i++){
            diceArray[i] = 1;
        }
    }

    // Methods

    /**
     * Throws the dice and returns the sum. Changes the diceArray array.
     * @return Sum
     */

    public Integer throwAndSum() {
        Integer sum = 0;
        for (int i = 0; i < numberOfDice; i++) {
            Integer dieResult = RandomNumber.getRandomNumber(6);
            sum += dieResult;
            diceArray[i] = dieResult;
        }
        return sum;
    }

    /**
     * Gets the numberOfDice
     * @return
     */

    public Integer getNumberOfDice(){
        return this.numberOfDice;
    }

    /**
     * Gets the diceArray
     * @return
     */

    public Integer[] getDiceArray(){
        return this.diceArray;
    }
}
