package io.zipcoder.casino.BlackJack;

public class Player {




        private String name;
        // the cards in the players hand
        private BlackJackCard[] hand = new BlackJackCard[10];
        //number of cards in the hand
        private int numCards;


        // constructing a new player
        public void Player (String aName){
            this.name = aName;

            this.emptyHand();



        }
        //reset the players hand to have no cards
        private void emptyHand() {
            for (int card = 0; card < 10 ; card++) {
                this.hand[card] = null;
            }
            this.numCards = 0;
        }
        //adding a card to the players hand
        public boolean  addCard(BlackJackCard aCard){


            // print an error if we already have the max number of cards

            if(this.numCards==10){
                System.out.printf("%s's hand already has 10 cards; " + "cannot add another \n", this.name);
                System.exit(1);

            }
            this.hand[this.numCards] = aCard;
            this.numCards++;

            return(this.getHandSum()<=21);
        }

        public int getHandSum() {
            int handSum = 0;
            int cardNumber;
            int numAces = 0;
            //calc each cards contribution to the hand sum
            for (int card = 0; card <this.numCards ; card++) {
                //get the number of the current card
                cardNumber = this.hand[card].getCardNumber();

                if(cardNumber ==1 ){  // Ace
                    numAces++;
                    handSum += 11;

                } else if(cardNumber > 10){ // face card --> king queen jack etcc..
                    handSum += 10;

                }else{
                    handSum+= cardNumber;
                }

            }
            // converting 11 for aces into 1 as necessary
            while(handSum > 21 && numAces > 0){
                handSum -= 10;
                numAces--;

            }

            return handSum;
        }
        //print cards in players hand-> param-> showFirstCard whether the first card is hidden
        public void printHand(boolean showFirstCard){
            System.out.printf("%s's cards : \n", this.name);
            for (int card = 0; card < this.numCards ; card++)

                if (card == 0 && !showFirstCard) {
                    System.out.println(" [hidden]");
                } else {
                    System.out.printf("  %s\n", this.hand[card].toString());
                }

        }

    }



