package io.zipcoder.casino;


import io.zipcoder.casino.BlackJack.BlackJackGame;
import io.zipcoder.casino.ChoHan.ChoHanGame;
import io.zipcoder.casino.Craps.CrapsGame;

import io.zipcoder.casino.GoFish.GoFishGame;

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

    // Constructor

    public Casino(){
        playerList = new ArrayList<Player>();
        houseBalance = 0;
    }

    public Casino(Console console){
        this.console = console;
        playerList = new ArrayList<Player>();
        houseBalance = 0;
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
            if(nextAction == 9){
                exitFlag = true;
            }
            // Perform the next action
            performNextAction(nextAction);
        }
    }

    private void performNextAction(Integer nextAction) {
        if(nextAction.equals(1)){
            Player player = selectUserToPlay();
            // Start GoFish
            goFishGreeter();
            GoFishGame goFish = new GoFishGame(player);
            goFish.start();
        }
        else if(nextAction.equals(2)){
            Player player = selectUserToPlay();
            // Start BlackJack
            blackJackGreeter();
            BlackJackGame blackJack = new BlackJackGame(player);
            blackJack.start();
            // End sequence updates
            updatePlayerWallet(blackJack.getPlayerNetGain(), player);
            updateHouseBalance(blackJack.getHouseCommission());

        }
        else if(nextAction.equals(3)){
            Player player = selectUserToPlay();
            // Start Craps
            crapsGreeter();
            CrapsGame craps = new CrapsGame(player);
            craps.start();
            // End sequence updates
            updatePlayerWallet(craps.getPlayerNetGain(), player);
            updateHouseBalance(craps.getHouseCommission());
        }
        else if(nextAction.equals(4)){
            // Start ChoHan
            choHanGreeter();
            ChoHanGame choHan = new ChoHanGame(playerList);
            choHan.start();
            // End sequence updates
            updatePlayersWallets(choHan.getPlayerNetGain());
            updateHouseBalance(choHan.getHouseCommission());
        }
        else if(nextAction.equals(5)){
            goToATM();
        }
        else if(nextAction.equals(6)){
            checkHouse();
        }
        else if(nextAction.equals(7)){
            addPlayer();
        }
        else if(nextAction.equals(8)){
            checkWallets();
        }
        else{
            console.println("Either do something or get off the floor. We ... ugh I mean you got money to make!");
        }
    }

    void choHanGreeter() {
        console.println("\n" +
                "                                                                \n" +
                "    //   ) )                          //    / /                 \n" +
                "   //        / __      ___           //___ / /  ___       __    \n" +
                "  //        //   ) ) //   ) ) ____  / ___   / //   ) ) //   ) ) \n" +
                " //        //   / / //   / /       //    / / //   / / //   / /  \n" +
                "((____/ / //   / / ((___/ /       //    / / ((___( ( //   / /   \n");
    }

    void crapsGreeter() {
        console.println("\n" +
                "      ...                                                    .x+=:.   \n" +
                "   xH88\"`~ .x8X                                             z`    ^%  \n" +
                " :8888   .f\"8888Hf    .u    .                .d``              .   <k \n" +
                ":8888>  X8L  ^\"\"`   .d88B :@8c        u      @8Ne.   .u      .@8Ned8\" \n" +
                "X8888  X888h       =\"8888f8888r    us888u.   %8888:u@88N   .@^%8888\"  \n" +
                "88888  !88888.       4888>'88\"  .@88 \"8888\"   `888I  888. x88:  `)8b. \n" +
                "88888   %88888       4888> '    9888  9888     888I  888I 8888N=*8888 \n" +
                "88888 '> `8888>      4888>      9888  9888     888I  888I  %8\"    R88 \n" +
                "`8888L %  ?888   !  .d888L .+   9888  9888   uW888L  888'   @8Wou 9%  \n" +
                " `8888  `-*\"\"   /   ^\"8888*\"    9888  9888  '*88888Nu88P  .888888P`   \n" +
                "   \"888.      :\"       \"Y\"      \"888*\"\"888\" ~ '88888F`    `   ^\"F     \n" +
                "     `\"\"***~\"`                   ^Y\"   ^Y'     888 ^                  \n" +
                "                                               *8E                    \n" +
                "                                               '8>                    \n" +
                "                                                \"                     \n");
    }

    void goFishGreeter() {
        console.println("\n" +
                "  @@@@@@@   @@@@@@  @@@@@@@@ @@@  @@@@@@ @@@  @@@\n" +
                " !@@       @@!  @@@ @@!      @@! !@@     @@!  @@@\n" +
                " !@! @!@!@ @!@  !@! @!!!:!   !!@  !@@!!  @!@!@!@!\n" +
                " :!!   !!: !!:  !!! !!:      !!:     !:! !!:  !!!\n" +
                "  :: :: :   : :. :   :       :   ::.: :   :   : :\n" +
                "                                                 \n");
    }

    void blackJackGreeter() {
        console.println("\n" +
                "                                                                                                              \n" +
                "     ***** **   ***                             *                ***** **                            *        \n" +
                "  ******  ***    ***                          **              ******  **** *                       **         \n" +
                " **   *  * **     **                          **             **   *  * ****                        **         \n" +
                "*    *  *  **     **                          **            *    *  *   **                         **         \n" +
                "    *  *   *      **                          **                *  *                               **         \n" +
                "   ** **  *       **       ****       ****    **  ***          ** **            ****       ****    **  ***    \n" +
                "   ** ** *        **      * ***  *   * ***  * ** * ***         ** **           * ***  *   * ***  * ** * ***   \n" +
                "   ** ***         **     *   ****   *   ****  ***   *        **** **          *   ****   *   ****  ***   *    \n" +
                "   ** ** ***      **    **    **   **         **   *        * *** **         **    **   **         **   *     \n" +
                "   ** **   ***    **    **    **   **         **  *            ** **         **    **   **         **  *      \n" +
                "   *  **     **   **    **    **   **         ** **            ** **         **    **   **         ** **      \n" +
                "      *      **   **    **    **   **         ******           ** **         **    **   **         ******     \n" +
                "  ****     ***    **    **    **   ***     *  **  ***          ** **         **    **   ***     *  **  ***    \n" +
                " *  ********      *** *  ***** **   *******   **   *** *       *  *           ***** **   *******   **   *** * \n" +
                "*     ****         ***    ***   **   *****     **   ***    **     *            ***   **   *****     **   ***  \n" +
                "*                                                         ****   *                                            \n" +
                " **                                                       *  * **                                             \n" +
                "                                                         *    **                                              \n" +
                "                                                              *                                               \n" +
                "                                                                                                              \n");
    }

    void checkWallets() {
        for(Player player : playerList){
            console.println("%s has %d chips in their stack.", player.getName(), player.getWallet());
        }
    }

    Player selectUserToPlay() {
        String name = console.getStringInput("Who is the lucky one who gets to play?");
        for(Player player : playerList){
            if(player.getName().toUpperCase().equals(name.toUpperCase())){
                return player;
            }
        }
        console.println("%s is not in your party. Who are you even talking about?", name);
        return selectUserToPlay();
    }

    void updateHouseBalance(Integer houseCommission) {
        houseBalance += houseCommission;
    }

    void updatePlayersWallets(ArrayList<Integer> playerNetGain) {
        for (int i = 0; i < playerList.size(); i++){
            playerList.get(i).updateWallet(playerNetGain.get(i));
        }
    }

    void updatePlayerWallet(Integer playerNetGain, Player playerToFind) {
        for (Player player: playerList){
            if (player.equals(playerToFind)){
                player.updateWallet(playerNetGain);
            }
        }
    }


    public void checkHouse() {
        console.println("The house has made %d tonight", houseBalance);
    }

    public void goToATM() {
        printATM();
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

    private void printATM() {
        console.println("\n" +
                " .----------------.  .----------------.  .----------------. \n" +
                "| .--------------. || .--------------. || .--------------. |\n" +
                "| |      __      | || |  _________   | || | ____    ____ | |\n" +
                "| |     /  \\     | || | |  _   _  |  | || ||_   \\  /   _|| |\n" +
                "| |    / /\\ \\    | || | |_/ | | \\_|  | || |  |   \\/   |  | |\n" +
                "| |   / ____ \\   | || |     | |      | || |  | |\\  /| |  | |\n" +
                "| | _/ /    \\ \\_ | || |    _| |_     | || | _| |_\\/_| |_ | |\n" +
                "| ||____|  |____|| || |   |_____|    | || ||_____||_____|| |\n" +
                "| |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------' \n");
    }


    public void getStartingPlayers() {
        int numberOfStartingPlayers = console.getIntegerInput("How many are we bringing today?");
        for (int i = 0; i < numberOfStartingPlayers; i++){
            addPlayer();
        }
    }

    public void addPlayer() {
        String name = console.getStringInput("Please enter the name of our new guest");
        int wallet = console.getIntegerInput("And how much would you like to start with today?");
        Player player = new Player(name, wallet);
        this.playerList.add(player);
    }

    Integer getNextAction() {
        String toShow = "Please Select From The Following Options \n" +
                "(1) GoFish (2) BlackJack (3) Craps (4) Cho-Han\n" +
                "(5) ATM (6) Check House (7) Add Player (8) Check Wallet (9) Exit";
        return console.getIntegerInput(toShow);
    }

    Integer getNumberOfPlayers() {
        return playerList.size();
    }

    public void printBanner(){
        String banner = "\n" +
             //   "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
                "______  ______        ______       ________      ___________                _________             _____              \n" +
                "___  / / /__(_)______ ___  /_      ___  __ \\________  /__  /____________    __  ____/_____ __________(_)____________ \n" +
                "__  /_/ /__  /__  __ `/_  __ \\     __  /_/ /  __ \\_  /__  /_  _ \\_  ___/    _  /    _  __ `/_  ___/_  /__  __ \\  __ \\\n" +
                "_  __  / _  / _  /_/ /_  / / /     _  _, _// /_/ /  / _  / /  __/  /        / /___  / /_/ /_(__  )_  / _  / / / /_/ /\n" +
                "/_/ /_/  /_/  _\\__, / /_/ /_/      /_/ |_| \\____//_/  /_/  \\___//_/         \\____/  \\__,_/ /____/ /_/  /_/ /_/\\____/ \n" +
                "              /____/                                                                                                 \n" +
                "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$";
        console.println(banner);
    }

    public void printSlogan() {
        String slogan = "If You Put Down Cents, We're Throwing You Out";
        console.println(slogan);
    }

    public void welcomePlayers() {
        String welcome = "Welcome ";
        for (int i = 0; i < playerList.size() - 1; i++){
            welcome += playerList.get(i).getName() + " ";
        }
        if (playerList.size() > 1){
            welcome += "and " + playerList.get(playerList.size()-1).getName() + "!";
        }
        else{
            welcome += playerList.get(0).getName() + "!";
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

