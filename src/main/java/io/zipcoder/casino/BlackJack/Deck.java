package io.zipcoder.casino.BlackJack;


import java.util.Collections;
import java.util.Stack;

public class Deck {
    public Suit suit;
    public CardNumbers cardNumbers;
    public Stack<BlackJackCard> myCards;




    // this constructor is saying the number of decks (how many sets of 52 cards are in the deck) and whether
    //it should be shuffled (numDeck and shuffle)
    public void makeBlackJackDeck() {


        //for loop for each deck
        //for (int deck = 0; deck <numDeck ; deck++) {

        // for loop for each suit
        for (Suit currentSuit : Suit.values()) {

            // for loop for each number
            for (CardNumbers thisCardNumber : CardNumbers.values()) {

                myCards.push(new BlackJackCard(currentSuit, thisCardNumber));

            }
        }
    }


    // shuffle deck randomly swapping pairs to shuffle the deck o cards
    private void shuffle() {
        Collections.shuffle(myCards);


    }


    // deal next card in the deck
    public BlackJackCard dealNxtCard() {


        return myCards.pop();
    }
}

//print top card in the deck
//numToPrint the # of cards from the top of the deck to print
//        public void  printDeck(int numToPrint){
//
//            for (int card = 0; card < numToPrint ; card++) {
//                // printF formatters-> % 3d -> print an integer whose width is 3 and everything else is padded with spaces
//                // /%d -->print an integer
//                // %s --> print a string
//                // \n --> line break
//                System.out.printf("% 3d/%d %s \n",card+1,this.numCards,
//                        this.myCards[card].toString());
//
//            }
//            System.out.printf("\t\t[%d other] \n", this.numCards-numToPrint);
//
//        }




