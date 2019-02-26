package io.zipcoder.casino.BlackJack;

import io.zipcoder.casino.utilities.Player;

public class BlackJackRunner {

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(new Player("Player" , 10000));
        blackJackGame.playRound();
    }

}
