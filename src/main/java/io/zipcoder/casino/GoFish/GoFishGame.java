package io.zipcoder.casino.GoFish;


import io.zipcoder.casino.utilities.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;




public class GoFishGame {


    /**
     * playerhand.getHandList.size()
     * Card.getValue ( 1-13) ie. 1 Ace, 13 King
     */
//Instance Variables

    private Player player;
    private Hand playerHand;
    private Hand opponentHand;

    private ArrayList<Integer> playerBook;
    private ArrayList<Integer> opponentBook;

    private Console console = new Console(System.in, System.out);

    private boolean endGameCheck;
    private boolean win;
    private int turnOrder;

    private Deck deck;
    private int playerBookCount;
    private int opponentBookCount;


    //Constructor
    public GoFishGame(Player player) {
        this.player = player;
        this.playerHand = new Hand();
        this.opponentHand = new Hand();
        playerBook = new ArrayList<Integer>(13);
        opponentBook = new ArrayList<Integer>(13);
        endGameCheck = false;
        this.deck = new Deck();
        deck.shuffle();

        turnOrder = randomTurnOrder();
    }


    public int randomTurnOrder() {
        Random random = new Random();
        return random.nextInt(2);
    }



    public void start() {

        startOfGame();
        displayBookCheck();

     //   turnOrder = playerGoesFirst();

        while (!endGameCheck) {
    //        gameTurnOrder(turnOrder);
            playerTurn();
            bookTurn();
            checkGameOver();
            if(!endGameCheck) {
                opponentTurn();
                opponentBookTurn();

                checkGameOver();
            }
        }

        console.println("Thanks for playing! \n ... Returning to main menu.");
    }

    public void checkGameOver() {
        endGameCheck = (playerBook.size() + opponentBook.size() == 13 || opponentHand.getHandList().size()==0);
        if (playerBook.size() > opponentBook.size() && endGameCheck) {
            console.println("Player wins");
        } else if (playerBook.size() < opponentBook.size() && endGameCheck) {
            console.println(("Opponent wins"));
        }
    }


    /**
     * game start method
     */
    public void startOfGame() {
        deck.refill();
        Collections.shuffle(deck.getDeck());
        //deck.shuffle();
//        playerHand.emptyHand();
//        opponentHand.emptyHand();
//        playerBook.isEmpty();
//        opponentBook.isEmpty();
        playerHand.addtoHand(deck.dealNFromTop(5));
        opponentHand.addtoHand(deck.dealNFromTop(5));

      //  System.out.println(opponentHand.getHandList().size());
      //  System.out.println(deck.getDeck().size());
    }

    /**
     * book size
     */

    private void displayBookCheck() {
        console.println("Player has %d books. Opponent has %d books.", playerBook.size(), opponentBook.size());
    }

    /**
     * taking game turn orders
     */

    public void gameTurnOrder(int turnOrder) {
        if (turnOrder == 0) {
            playerTurn();
            opponentTurn();


        } else {
            opponentTurn();
            playerTurn();

        }
    }


    /**
     * method to  either player/opponent to starts game
     */

    private int playerGoesFirst() {
        console.println("who wants to goes first");

        if (this.turnOrder == 0) {
            console.println("player goes first");
            return 0;
        } else {
            console.println("opponent goes first");
        }
        return 1;
    }

    /**
     * method to  add card to hand
     */

    public void increaseTotalCards(boolean isPlayersTurn) {
        if (isPlayersTurn) {
            playerHand.addCardToHand(deck.cardDealFromTop());
        } else {
            opponentHand.addCardToHand(deck.cardDealFromTop());
        }
    }

    /** play gofish game here */


    /**
     * logic for players turn
     */

    public void playerTurn() {

        String card = console.getStringInput("what card do you want to ask for?");
        Integer parsedCard;

        if (card.toUpperCase().equals("K")) {
            parsedCard = 13;
        } else if (card.toUpperCase().equals("J")) {
            parsedCard = 11;
        } else if (card.toUpperCase().equals("A")) {
            parsedCard = 1;
        } else if (card.toUpperCase().equals("Q")) {
            parsedCard = 12;
        } else {
            parsedCard = Integer.getInteger(card);
        }


        if (opponentHand.hasCard(parsedCard)) {
            playerHand.addtoHand(opponentHand.getCards(parsedCard));
        } else
            console.println("Go fish! You drew " + GoFish(playerHand));

    }

    /**
     * player or opponent says Go fish
     */

    private String GoFish(Hand hands) {
        if(deck.isEmpty()){
            return "No Cards in Deck";
        }
        else {

            Card card = deck.cardDealFromTop();

            hands.addCardToHand(card);


            return card.toString();
        }
    }




    /**
     * logic for opponent turn asking for card
     */

    private void opponentTurn() {

        Integer card = opponentHand.getHandList().get(0).getValue();
        //Card card = new Card("Hearts", 13);
        if (playerHand.hasCard(card)) {
            opponentHand.addtoHand(playerHand.getCards(card));
        } else
            GoFish(opponentHand);
    }


    /**
     * Books turn -  remove card from playerhand
     * checking the count how many books player has
     */


    private void bookTurn() {
        List<Card> allBooks = playerHand.bookEvalute();
        if (allBooks.size() > 0) {
            console.println("You've played down a book");
            playerHand.getHandList().removeAll(allBooks);
            playerBookCount += allBooks.size() / 4;
        }
    }

    private void opponentBookTurn() {

        List<Card> opponentAllBooks = opponentHand.bookEvalute();
        if (opponentAllBooks.size() > 0) {
            console.println("Opponent played down a book");
            opponentHand.getHandList().removeAll(opponentAllBooks);
            opponentBookCount += opponentAllBooks.size() / 4;
        }
    }
}
