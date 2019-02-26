package io.zipcoder.casino;

import io.zipcoder.casino.utilities.Console;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CasinoTest {



    @Test
    public void printBannerTest() {
        // Given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Console console = getConsoleWithBufferedInputAndOutput("", baos);
        Casino casino = new Casino(console);

        casino.printBanner();

        String banner = "\n" +
              //  "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
                "______  ______        ______       ________      ___________                _________             _____              \n" +
                "___  / / /__(_)______ ___  /_      ___  __ \\________  /__  /____________    __  ____/_____ __________(_)____________ \n" +
                "__  /_/ /__  /__  __ `/_  __ \\     __  /_/ /  __ \\_  /__  /_  _ \\_  ___/    _  /    _  __ `/_  ___/_  /__  __ \\  __ \\\n" +
                "_  __  / _  / _  /_/ /_  / / /     _  _, _// /_/ /  / _  / /  __/  /        / /___  / /_/ /_(__  )_  / _  / / / /_/ /\n" +
                "/_/ /_/  /_/  _\\__, / /_/ /_/      /_/ |_| \\____//_/  /_/  \\___//_/         \\____/  \\__,_/ /____/ /_/  /_/ /_/\\____/ \n" +
                "              /____/                                                                                                 \n" +
                "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n";

        Assert.assertEquals(banner, baos.toString());
    }

    @Test
    public void printSloganTest() {
        // Given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Console console = getConsoleWithBufferedInputAndOutput("", baos);
        Casino casino = new Casino(console);

        casino.printSlogan();

        String slogan = "If You Put Down Cents, We're Throwing You Out\n";

        Assert.assertEquals(slogan, baos.toString());
    }

//    @Test
    public void addPlayerTest(){
        // Given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Console console = getConsoleWithBufferedInputAndOutput("Will\n500", baos);
        Casino casino = new Casino(console);

        casino.addPlayer();

        Integer playerCount = casino.getNumberOfPlayers();
        Integer expected = 1;

        casino.welcomePlayers();
        String expected2 = "Please enter the name of our new guest\n" +
                "And how much would you like to start with today?\n" +
                "Welcome Will \n";

//        Assert.assertEquals(expected, playerCount);
//        Assert.assertEquals(expected2, baos.toString());
    }

    @Test
    public void getStartingPlayersTest(){
        // Given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Console console = getConsoleWithBufferedInputAndOutput("2\nWill\n500\nGrace\n400", baos);
        Casino casino = new Casino(console);

        casino.getStartingPlayers();

        Integer playerCount = casino.getNumberOfPlayers();
        Integer expected = 2;

        casino.welcomePlayers();
        String expected2 = "How many are we bringing today?\n" +
                "Please enter the name of our new guest\n" +
                "And how much would you like to start with today?\n" +
                "Please enter the name of our new guest\n" +
                "And how much would you like to start with today?\n" +
                "Welcome Will Grace \n";

        Assert.assertEquals(expected, playerCount);
        Assert.assertEquals(expected2, baos.toString());
    }

    @Test
    public void setHouseBalanceTest() {
        // Given
        Casino casino = new Casino();
        int actual1 = casino.getHouseBalance();
        int expected1 = 0;

        casino.setHouseBalance(500);
        int actual2 = casino.getHouseBalance();
        int expected2 = 500;
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void checkHouseTest() {
        // Given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Console console = getConsoleWithBufferedInputAndOutput("", baos);
        Casino casino = new Casino(console);
        String expected = "The house has made 500 tonight\n";

        casino.setHouseBalance(500);
        casino.checkHouse();
        Assert.assertEquals(expected, baos.toString());
    }



    public Console getConsoleWithBufferedInputAndOutput(String input, ByteArrayOutputStream baos) {
        Console testConsole;
        if (input == null) {
            testConsole = new Console(System.in, new PrintStream(baos));
        } else {
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            testConsole = new Console(bais, new PrintStream(baos));
        }
        return testConsole;
    }

}
