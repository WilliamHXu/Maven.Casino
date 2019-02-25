package io.zipcoder.casino.GoFish;

import io.zipcoder.casino.utilities.Card;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Deck;
import io.zipcoder.casino.utilities.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

public class GoFishGame {



    private ArrayList<Player> playerList;

    private ArrayList<Card> playerHand;
    private Deck opponentHand;

    private Card[] playerBookCheck;
    private Card[] opponentBookCheck;

    private HashMap<String, Integer> allRanks;


    private boolean endGameCheck;

    private int playerBook;
    private int opponentBook;

    private int playerTotalCards;
    private int opponentTotalCards;

    private int turnOrder;


    public GoFishGame() {
        this.playerList = playerList;

        playerBookCheck = new Card[13];
        opponentBookCheck = new Card[13];

        playerBook = 0;
        opponentBook = 0;
        endGameCheck = false;

        playerTotalCards = 0;
        opponentTotalCards = 0;

        turnOrder = randomTurnOrder();
    }



    private int randomTurnOrder() {
        Random random = new Random();
        return random.nextInt(2);
    }


    public void play() {

//        startOfGame();
//        firstBookCheck();


        turnOrder = playerGoesFirst();

        while (!endGameCheck) {
            gameTurnOrder(turnOrder);
        }

       // Console.print("Thanks for playing! \n ... Returning to main menu.");
    }




    public void gameTurnOrder(int turnOrder) {
        if (turnOrder == 0) {
            playerTurn();
            opponentTurn();


        } else {
            opponentTurn();
            playerTurn();

        }
    }


    private int playerGoesFirst() {

        if (this.turnOrder == 0) {
            return 0;
        } else {
            return 1;
        }
    }


   public void increaseTotalCards(boolean isPlayersTurn){
        if( isPlayersTurn){
            incrementPlayersCard();
        }else{
            incrementOpponentCard();
        }
        }

    public void addBookCountDecreaseCardTotal(boolean isPlayersTurn) {
        if (isPlayersTurn) {
            playerTurnTrue();
        } else {
            opponentTurnTrue();
        }
    }


    public void goFish(Card[] playerBookCheck, boolean isPlayersTurn){
        //put go fish here
//        Card newCard = decklist.dealCard();
//        int newCardIndex = newCard.getValue().getValue() - 1;
//
//        Console.print("Adding " + newCard.toString() + " to the hand.");
//
//        if (hand[newCardIndex] != null) {
//            removeBook(hand, newCardIndex);
//            addBookCountDecreaseCardTotal(isPlayersTurn);
//
//        } else {
//            hand[newCardIndex] = newCard;
//            increaseTotalCards(isPlayersTurn);
//
//        }
   }




    private void playerTurn(){

        boolean validCardAsked = false;

      //  printSortedHand();
        if(playerTotalCards ==0){
     //      ArrayList<Card> newCard =  deckList.dealNFromTop();

         //  int newCardIndex =newCard.getValue().getValue() - 1;
       //    playerBookCheck[newCardIndex]  = newCard;
           incrementPlayersCard();
        }

        while (!validCardAsked) {
            String cardAskedFor = startPlayerTurn();
            checkingOpponentHand();
            if (checkHandForCard(playerBookCheck, cardAskedFor)) {
                if (!addAsktoHand(opponentBookCheck, playerBookCheck, cardAskedFor, true)) {
                 //   Console.print("No cards of that number found, sorry!\nGO FISH!");
                    goFish(playerBookCheck, true);
                }
                validCardAsked = true;
            } else {
              //  Console.print("You don't have that card! Try again...");
            }
        }

        checkPlayerBook();
        endGameCheck = endGame();
        endCheck(true);



    }

    private String startPlayerTurn() {

//        String userAsk = Console.printAndTakeString("What card do you want to ask for?");
//
//        if (!checkRank(userAsk)) {
//            userAsk = Console.printAndTakeString("Invalid entry type,"
//                    + "try typing out the card number/face.. ie. Ace or two\n");
//        }

        //return userAsk.toLowerCase();
        return null;
    }

    private boolean addAsktoHand(Card[] takeFromThisHand, Card[] addToThisHand, String userAsk, boolean isPlayersTurn) {
        Card requestedCard = takeFromThisHand[allRanks.get(userAsk)];

        if (requestedCard != null) {
            takeFromThisHand[allRanks.get(userAsk)] = null;

            if (addToThisHand[allRanks.get(userAsk)] != null) {

            }
            addToThisHand[allRanks.get(userAsk)] = null;
            addBookCountDecreaseCardTotal(isPlayersTurn);

        } else {

            addToThisHand[allRanks.get(userAsk)] = requestedCard;
            increaseTotalCards(isPlayersTurn);

        }
        return requestedCard != null;
    }







    private void checkingOpponentHand() {
//        Console.print("Checking opponent's hand...");
//        Console.pause(250, " . ", 5);
    }



    private boolean checkHandForCard(ArrayList<Card> cards, String string) {
        return cards.get(allRanks.get(string)) !=null;
    }

    private void checkPlayerBook() {
//        Console.print("\nChecking for books... ");
//        Console.pause(350, " . ", 5);
//        Console.print("You have " + playerBook + " book(s).\n");
    }

    private void opponentTurn(){

        opponentAsk();
        endGameCheck = endGame();
        endCheck(false);
    }


    private void opponentAsk(){

        Random random = new Random();
        if(opponentTotalCards ==0){
//            ArrayList<Card> newCard =  deckList.dealNFromTop();
//
//            int newCardIndex =newCard.getValue().getValue() - 1;
//          //  opponentBookCheck  how to check
            incrementOpponentCard();
        }
        int result = random.nextInt(opponentTotalCards);
        int counter =0;
        Card randomCard = null;

//        for (int i = 0; i < opponentBookCheck.size(); i++) {
//           if(opponentBookCheck[i] != null){
//                counter++;
//                randomCard = opponentBookCheck[i];
//                if(counter>=result){
//                    break;
//                }
//            }

        }





    private boolean endGame() {
        return false;
    }


    private void endCheck(boolean isPlayerTurn) {
        if (endGameCheck) {
            // printResult();
        } else {
            if (isPlayerTurn && playerTotalCards == 0) {

           //     notGameOver(playerBookCheck);
                incrementPlayersCard();

            } else if (opponentTotalCards == 0) {

             //   notGameOver(opponentBookCheck);
                incrementOpponentCard();
            }

        }

    }

    private void notGameOver(ArrayList<Card> bookCheck) {
//        Card newCard = decklist.dealNTop();
//        bookCheck[newCard.getValue().getValue() - 1] = newCard ;

    }



    public void decrementPlayersCards() {
        setPlayerTotalCards(getPlayerTotalCards() - 1);
    }

    public void decrementOpponentCards() {
        setOpponentTotalCards(getOpponentTotalCards() - 1);
    }

    private boolean checkHandForCard(Card[] cards, String string) {
        return cards[allRanks.get(string)] != null;
    }






    public void setPlayerTotalCards(int playerTotalCards){
        this.playerTotalCards = playerTotalCards;
    }

    public int getPlayerTotalCards(){
        return playerTotalCards;
    }

    public void incrementOpponentCard() {
      setPlayerTotalCards(getPlayerTotalCards() + 1);

    }

    public void incrementPlayersCard() {
       setOpponentTotalCards(getOpponentTotalCards() + 1);
    }

    public void setOpponentTotalCards(int opponentTotalCards){
        this.opponentTotalCards = opponentTotalCards;
    }
    public int getOpponentTotalCards(){
        return opponentTotalCards;
    }

    public void incrementPlayersBook() {
        setPlayerBook(getPlayerBook() + 1);
    }

    public void incrementOpponentBook() {
        setOpponentBook(getOpponentBook() + 1);
    }
    public int getPlayerBook() {
        return playerBook;
    }

    public void setPlayerBook(int playerBook) {
        this.playerBook = playerBook;
    }

    public int getOpponentBook() {
        return opponentBook;
    }

    public void setOpponentBook(int opponentBook) {
        this.opponentBook = opponentBook;
    }



    private void opponentTurnTrue() {
        incrementOpponentBook();
        decrementOpponentCards();
        decrementOpponentCards();
    }


    private void playerTurnTrue() {
        incrementPlayersBook();
        decrementPlayersCards();
        decrementPlayersCards();
    }

    }
