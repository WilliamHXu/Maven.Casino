package io.zipcoder.casino.BlackJack;

import io.zipcoder.casino.utilities.Card;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Hand;
import io.zipcoder.casino.utilities.Player;

import java.util.ArrayList;

public class BlackJackPlayer extends Player {
    private Hand hand;

    // Constructor
    public BlackJackPlayer(Player player){
        super.playerName = player.getName();
        super.walletBalance = player.getWallet();
        super.playerNetGainLoss = player.getPlayerNetGainLoss();
        this.hand = new Hand();
    }

    //reset the players hand to have no cards
    public void emptyHand() {
        this.hand.emptyHand();
    }

    public void addCard(Card card){
        this.hand.addCardToHand(card);
    }

    public int getHandSum(){
        int handSum = 0;
        int numAces = 0;
        ArrayList<Card> handList = hand.getHandList();
        // Calculate each card's contribution to handSum
        for (Card card : handList){
            // Check for Aces
            if(card.getValue().equals(1)){
                numAces++;
                handSum += 11;
            }
            // Check for Faces
            else if(card.getValue().equals(11) || card.getValue().equals(12) || card.getValue().equals(13)) {
                handSum += 10;
            }
            // Increment All Others
            else {
                handSum += card.getValue();
            }
        }
        // Deal with Aces
        while (handSum > 21 && numAces > 0){
            handSum -= 10;
            numAces--;
        }
        return handSum;
    }

    public String getHandString(boolean showFirstCard){
        Console console = new Console(System.in, System.out);
        console.println("%s's cards : ", this.playerName);
        ArrayList<Card> handList = this.hand.getHandList();
        String handString = "";
        for (int cardIndex = 0; cardIndex < handList.size(); cardIndex++){
            if (cardIndex == 0 && !showFirstCard){
                handString += " [hidden] ";
            }
            else {
                handString += " " + handList.get().toString();
            }
        }
        return handString;
    }

}
