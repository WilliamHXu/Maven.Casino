package io.zipcoder.casino.ChoHan;

import io.zipcoder.casino.utilities.Player;

import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
        Player player1 = new Player("Will", 1000);
        Player player2 = new Player("Nirmala", 1000);
        Player player3 = new Player("Laxmi", 6000);
        Player player4 = new Player("Shaun", 3000);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        ChoHanGame game = new ChoHanGame(players);
        game.start();

    }
}