package io.zipcoder.casino;


import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Player;

import java.util.ArrayList;

public class Casino {
    public ArrayList<Player> playerList;
    public boolean exitFlag = false;
    public Game currentGame;
    private Integer houseBalance;
    Console console = new Console(System.in, System.out);

    public static void main(String[] args) {
        // write your tests before you start fucking with this
    }

    public void start(){
        // Introduce the Banner
        printBanner();
        // Introduce the Slogan
        printSlogan();
        // Ask for Starting Players
        getStartingPlayers();
        // Welcome Players
        welcomePlayers();
        // While Loop This
        while (!exitFlag){
            // Get Next Action
            Integer nextAction = getNextAction();
            // Check for Exit
            if(nextAction == 8){
                exitFlag = true;
            }
            // Perform the next action
            performNextAction(nextAction);
        }
    }

    private void performNextAction(Integer nextAction) {
//        if(nextAction.equals(1)){
//            // Start GoFish
//            Game goFish = new GoFishGame(playerList);
//            goFish.start();
//        }
//        else if(nextAction.equals(2)){
//            // Start BlackJack
//            Game blackJack = new BlackJackGame(playerList);
//            blackJack.start();
//            // End sequence updates
//
//        }
//        else if(nextAction.equals(3)){
//            // Start Craps
//            Game craps = new CrapsGame(playerList);
//            craps.start();
//            // End sequence updates
//
//        }
//        else if(nextAction.equals(4)){
//            // Start ChoHan
//            Game choHan = new ChoHanGame(playerList);
//            choHan.start();
//            // End sequence updates
//
//        }
//        else if(nextAction.equals(5)){
//            goToATM();
//        }
//        else if(nextAction.equals(6)){
//            checkHouse();
//        }
//        else if(nextAction.equals(7)){
//            addPlayer();
//        }
//        else{
//            console.println("Either enter a valid option or get off the floor.");
//        }
    }

    private void checkHouse() {
        console.println("The house has made %d tonight", houseBalance);
    }

    private void goToATM() {
        console.println("Welcome to the HRC ATM!");
        String name = console.getStringInput("Who needs to withdraw money?");
        boolean withdrawFlag = false;
        for(Player player : playerList){
            if(player.getName().toUpperCase().equals(name.toUpperCase())){
                int withdraw = console.getIntegerInput("How much would you like to withdraw?");
                player.goToATM(withdraw);
                withdrawFlag = true;
            }
        }
        // If there was no withdraw
        if (!withdrawFlag){
            console.println("Please enter a valid name");
            goToATM();
        }
        else {
            console.println("Thank you for your business!");
        }
    }


    private void getStartingPlayers() {
        int numberOfStartingPlayers = console.getIntegerInput("How many are we bringing today?");
        for (int i = 0; i < numberOfStartingPlayers; i++){
            addPlayer();
        }
    }

    private void addPlayer() {
        String name = console.getStringInput("Please enter the name of our new guest");
        int wallet = console.getIntegerInput("And how much would you like to start with today?");
        Player player = new Player(name, wallet);
        this.playerList.add(player);
    }

    Integer getNextAction() {
        String toShow = "Please Select From The Following Options \n" +
                "(1) GoFish (2) BlackJack (3) Craps (4) Cho-Han\n" +
                "(5) ATM (6) Check House (7) Add Player (8) Exit";
        return console.getIntegerInput(toShow);
    }

    Integer getNumberOfPlayers() {
        return playerList.size();
    }

    private void printBanner(){
        String banner = "\n" +
                "______  ______        ______       ________      ___________                _________             _____              \n" +
                "___  / / /__(_)______ ___  /_      ___  __ \\________  /__  /____________    __  ____/_____ __________(_)____________ \n" +
                "__  /_/ /__  /__  __ `/_  __ \\     __  /_/ /  __ \\_  /__  /_  _ \\_  ___/    _  /    _  __ `/_  ___/_  /__  __ \\  __ \\\n" +
                "_  __  / _  / _  /_/ /_  / / /     _  _, _// /_/ /  / _  / /  __/  /        / /___  / /_/ /_(__  )_  / _  / / / /_/ /\n" +
                "/_/ /_/  /_/  _\\__, / /_/ /_/      /_/ |_| \\____//_/  /_/  \\___//_/         \\____/  \\__,_/ /____/ /_/  /_/ /_/\\____/ \n" +
                "              /____/                                                                                                 \n";
        console.println(banner);
    }

    private void printSlogan() {
        String slogan = "If You Put Down Cents, We're Throwing You Out";
        console.println(slogan);
    }

    private void welcomePlayers() {
        String welcome = "Welcome ";
        for (Player player : playerList){
            welcome += player.getName() + " ";
        }
        console.println(welcome);
    }

    public Integer getHouseBalance()
    {
        return houseBalance;
    }

    public void setHouseBalance(Integer balance)
    {
        houseBalance = balance;
    }
}
