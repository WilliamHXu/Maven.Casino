package io.zipcoder.casino.BlackJack;

import io.zipcoder.casino.Interfaces.CardGame;
import io.zipcoder.casino.Interfaces.GamblingGame;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.utilities.Card;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Deck;
import io.zipcoder.casino.utilities.Player;

public class BlackJackGame implements Game, GamblingGame, CardGame {
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

    public void start(){

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
        boolean playerHit = false;
        boolean dealerhit = false;
        String answer;

        // Game Logic
        // Player's Turn
        while (!playerHit) {
            console.println("Hand Total: %d", player.getHandSum());
            answer = console.getStringInput("Hit or Stay? Enter H or S: ").toUpperCase();
            // If the player hits
            if (answer.compareToIgnoreCase("H") == 0) {
                // Add Next card in the deck and store whether player busted
                playerHit = !player.addCard(deck.cardDealFromTop());
                console.println(player.getHandString(true));
            } else {
                playerHit = true;
            }
        }
        // Dealer's Turn
        while (!dealerhit) {
            // Dealer Hits
            if (dealer.getHandSum() < 17) {
                console.println("The Dealer hits ");
                dealerhit = !dealer.addCard(deck.cardDealFromTop());
                console.println(dealer.getHandString(false));
            } else {
                console.println("The Dealer stays");
                dealerhit = true;
            }
        }

        // Print the final hands

        console.println("Dealer's Final Hand: %s", dealer.getHandString(true));
        console.println("Player's Final Hand: %s", player.getHandString(true));

        int playerSum = player.getHandSum();
        int dealerSum = dealer.getHandSum();

        // Determine Winner
        if(playerSum > dealerSum && playerSum <= 21 || dealerSum){
            //Player Wins
            player.updateWallet(totalMoney*2);
            console.println("Player Wins %d Dollars", totalMoney*2);
        }
        else {
            dealer.updateWallet(totalMoney);
            console.println("Dealer Wins");
        }

    }

    private int makeBet() {
        int bet = console.getIntegerInput("Please make a bet");
        player.updateWallet(-bet);
        dealer.updateWallet(bet);
    }

    public Integer wallet() {

        return null;
    }

    public Card hit() {
        return null;
    }

    public void stay() {
    }

    public void play() {
    }

    public void display() {
    }

    // Card Game Interface

    public void dealInitialHand() {
        player.addCard(deck.cardDealFromTop());
        dealer.addCard(deck.cardDealFromTop());
        player.addCard(deck.cardDealFromTop());
        dealer.addCard(deck.cardDealFromTop());
    }

    // Game Interface


    public Boolean playAgain() {
        return null;
    }

    void loadPlayer();

    String getNextPlayerAction();

    void displayGameState();

    void exit();
}
