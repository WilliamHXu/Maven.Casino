package io.zipcoder.casino.BlackJack;

import java.util.Scanner;

public class BlackJackGameRunner {



    public class BlackjackGameRunner {

        public static void main(String[] args){
            // initiate the eyes from our ole friend Mr. Scanner named sc
            Scanner sc = new Scanner(System.in);
            Deck theDeck = new Deck();
            theDeck.makeBlackJackDeck();

            // initiate the player objects
            Player blackJackPlayer = new Player("Player");
            Player blackJackDealer = new Player("Dealer");


            // deal the first card to the player
            blackJackPlayer.addCard(theDeck.dealNxtCard());
            //deal first card to dealer
            blackJackDealer.addCard(theDeck.dealNxtCard());
            // deal second card to the player
            blackJackPlayer.addCard(theDeck.dealNxtCard());
            // deal the second card to the Dealer
            blackJackDealer.addCard(theDeck.dealNxtCard());

            //print the initial hand
            System.out.println("Cards are dealt \n");
            blackJackPlayer.printHand(true);
            blackJackDealer.printHand(false);
            System.out.println("\n");

            //flags for when each player is finished hitting
            boolean playerHit =false;
            boolean dealerhit = false;
            String answer;

            while(!playerHit || !dealerhit){
                //players turn
                if(!playerHit){
                    System.out.print("Hit or Stay? (Enter H or S): ");
                    answer = sc.next();
                    System.out.println();
                    //if the player hits
                    if(answer.compareToIgnoreCase("H") == 0){
                        // add next card in the deck and store whether player is busted
                        playerHit = !blackJackPlayer.addCard(theDeck.dealNxtCard());
                        blackJackPlayer.printHand(true);
                    } else {
                        playerHit = true;

                    }
                }
                //if the dealer hits
                if(!dealerhit){
                    if(blackJackDealer.getHandSum() < 17 ){
                        System.out.println("The Dealer hits \n");
                        dealerhit = !blackJackDealer.addCard(theDeck.dealNxtCard());
                        blackJackDealer.printHand(false);
                    } else {
                        System.out.println("The Dealer stays\n");
                        dealerhit = true;

                    }

                }

                System.out.println();

            }
            //have to close the scanner
            sc.close();

            // print the final hands
            blackJackPlayer.printHand(true);
            blackJackDealer.printHand(true);

            int playerSum = blackJackPlayer.getHandSum();
            int dealerSum = blackJackDealer.getHandSum();

            if(playerSum > dealerSum && playerSum <= 21 || dealerSum > 21){
                System.out.println("Player wins");
            }else{
                System.out.println("Dealer wins");

            }
        }



    }

}
