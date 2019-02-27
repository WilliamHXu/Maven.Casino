package io.zipcoder.casino.BlackJack;


import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.utilities.Card;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Deck;
import io.zipcoder.casino.utilities.Player;

import java.util.ArrayList;

public class BlackJackGame implements Game {
    // Instance Variables
    private BlackJackPlayer player;
    private BlackJackPlayer dealer;
    private Deck deck;
    private Console console = new Console(System.in, System.out);


    // Utilities


    // Constructor

    public BlackJackGame(Player player) {
        this.player = new BlackJackPlayer(player);
        Player dealerPlayer = new Player("Dealer", 99999999);
        this.dealer = new BlackJackPlayer(dealerPlayer);
        this.deck = new Deck();
        this.deck.shuffle();
    }

    // Methods

    /**
     * Start Method
     */

    public void start() {
        blackJackGreeter();
        boolean keepPlaying = true;
        while (keepPlaying) {
            playRound();
            keepPlaying = askUserToPlay();
        }
    }

    public void blackJackGreeter() {
        String answer = console.getStringInput("Welcome to High Rollers BlackJack table!");
    }

    private boolean askUserToPlay() {
        String answer = console.getStringInput("Would you like to play another round?").toUpperCase();
        if (answer.equals("Y") || answer.equals("YES")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Plays a Round of BlackJack
     */
    public void playRound() {
        // Reach Initial Game State
        deck.refill();
        deck.shuffle();
        player.emptyHand();
        dealer.emptyHand();

        // Collect initial bets
        int initialBet = makeBet();
        int totalMoney = initialBet;

        // Deal Initial Hands
        dealInitialHand();

        // Print initial hands
        console.println("%s's Hand: %s", player.getName(), player.getHandString(true));
        console.println("%s's Hand: %s", dealer.getName(), dealer.getHandString(false));


        // Hit Flags
        //boolean dealerhit = false;

        // Game Logic
        // Player's Turn
        playerTurn();
        dealerTurn();
        // Dealer's Turn

        // Print the final hands

        console.println("Dealer's Final Hand: %s", dealer.getHandString(true));
        console.println("Player's Final Hand: %s", player.getHandString(true));

        int playerSum = player.getHandSum();
        int dealerSum = dealer.getHandSum();

        // Determine Winner

        if (playerSum > dealerSum && playerSum <= 21 && playerSum <= dealerSum) {
            //Player Wins
            player.updateWallet(totalMoney * 2);
            console.println("Player Wins %d Dollars", totalMoney * 2);


        } else if (playerSum == dealerSum) {
            console.println("Its a Tie! Push");
            player.updateWallet(totalMoney);


        } else if(playerSum == 21){
            player.updateWallet(totalMoney * 2);
            console.println("You have BlackJack! You Win!");


        } else if(dealerSum == 21){
            console.println("Dealer has BlackJack! You lose!");

        } else if(dealerSum > 21 ){
            player.updateWallet(totalMoney * 2);
            console.println("Dealer bust! You Win!");
        } else if(playerSum > 21 ){
            console.println("You lose this round, bust!");
        } else {
            dealer.updateWallet(totalMoney);
            console.println("Dealer Wins");


        }

    }

    private int makeBet() {


        int bet = console.getIntegerInput("Please make a bet : ");


        player.updateWallet(-bet);
        dealer.updateWallet(bet);
        return bet;
    }


    // Card Game Interface

    public void printHandState() {
        console.println("%s's Hand: %s", player.getName(), player.getHandString(true));
        console.println("%s's Hand: %s", dealer.getName(), dealer.getHandString(false));
    }

    public void dealInitialHand() {

        player.addCard(deck.cardDealFromTop());
        dealer.addCard(deck.cardDealFromTop());
        player.addCard(deck.cardDealFromTop());
        dealer.addCard(deck.cardDealFromTop());

    }

    public void playerTurn() {


        String answer;
        boolean playerHit = false;
        while (!playerHit) {
            console.println("Hand Total: %d", player.getHandSum());
            if (player.getHandSum() == 21) {
                break;

            }

            answer = console.getStringInput("Hit or Stay? Enter H or S: ").toUpperCase();
            // If the player hits
            if (answer.compareToIgnoreCase("H") == 0) {
                // Add Next card in the deck and store whether player busted
                player.addCard(deck.cardDealFromTop());
                playerHit = false;
                console.println(player.getHandString(true));
            } else {
                playerHit = true;
            }
            if (player.getHandSum() >= 21) {
                break;
            }
        }
    }

    public void dealerTurn() {
        boolean dealerhit = false;
        while (!dealerhit) {
            // Dealer Hits
            if (dealer.getHandSum() < 16) {
                console.println("The Dealer hits ");
                dealer.addCard(deck.cardDealFromTop());
                dealerhit = false;
                console.println(dealer.getHandString(false));
            } else {
                console.println("The Dealer stays");
                dealerhit = true;
            }
            if (dealer.getHandSum() == 21) {
                break;
            }
        }

    }

    public Integer getPlayerNetGain(){
        return this.player.getPlayerNetGainLoss();
    }

    public Integer getHouseCommission(){
        return this.dealer.getPlayerNetGainLoss();
    }

}
