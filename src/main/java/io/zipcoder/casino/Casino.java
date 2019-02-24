package io.zipcoder.casino;


import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.utilities.Console;

public class Casino {
    public java.util.ArrayList playerList;
    public Integer keepPlaying;
    public Game currentGame;
    private Integer houseBalance;
    Console console = new Console(System.in, System.out);

    public static void main(String[] args) {
        // write your tests before you start fucking with this
    }

    void getStartingPlayers() {
    }

    void addPlayer() {
    }

    String getNextAction() {
        return "";
    }

    Integer getNumberOfPlayers() {
        return 0;
    }

    public void printBanner{
        String banner = "\n" +
                "______  ______        ______       ________      ___________                _________             _____              \n" +
                "___  / / /__(_)______ ___  /_      ___  __ \\________  /__  /____________    __  ____/_____ __________(_)____________ \n" +
                "__  /_/ /__  /__  __ `/_  __ \\     __  /_/ /  __ \\_  /__  /_  _ \\_  ___/    _  /    _  __ `/_  ___/_  /__  __ \\  __ \\\n" +
                "_  __  / _  / _  /_/ /_  / / /     _  _, _// /_/ /  / _  / /  __/  /        / /___  / /_/ /_(__  )_  / _  / / / /_/ /\n" +
                "/_/ /_/  /_/  _\\__, / /_/ /_/      /_/ |_| \\____//_/  /_/  \\___//_/         \\____/  \\__,_/ /____/ /_/  /_/ /_/\\____/ \n" +
                "              /____/                                                                                                 \n";
        console.println(banner);
    }
}
