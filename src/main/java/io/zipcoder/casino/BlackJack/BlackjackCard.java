package io.zipcoder.casino.BlackJack;

class BlackJackCard {





    //one of the 4 suits for this card
    public  Suit cardSuit;


    public CardNumbers cardNumber;

    // card constructor aSuit is the suit of card aNumber is the number of the card
    public  BlackJackCard(Suit aSuit, CardNumbers aNumber){
        this.cardSuit=  aSuit;
        this.cardNumber=aNumber;



    }
    //returns number of the card
    public  CardNumbers getCardNumber(){
        return cardNumber;
    }

//    public String toString(){
//        String numStr = "something went wrong";
//        switch(this.cardNumber){

//            case 2:
//                numStr ="Two";
//                break;
//            case 3:
//                numStr ="Three";
//                break;
//            case 4:
//                numStr ="Four";
//                break;
//            case 5:
//                numStr ="Five";
//            case 6 :
//                numStr ="Six";
//            case 7 :
//                numStr ="Seven";
//            case 8:
//                numStr ="Eight";
//                break;
//            case 9 :
//                numStr ="Nine";
//                break;
//            case 10 :
//                numStr = "Ten";
//                break;
//            case 11 :
//                numStr = "Jack";
//                break;
//            case 12 :
//                numStr = "Queen";
//                break;
//            case 13 :
//                numStr = "King";
//                break;
//            case 1 :
//                numStr= "Ace";



//        }
//        return numStr + " of " + cardSuit.toString();
//    }

}


