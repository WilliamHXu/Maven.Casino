package io.zipcoder.casino;


import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.Console;

public class CasinoTest {

    @Test
    public void printBannerTest(){
        // Given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Console console = getConsoleWithBufferedInputAndOutput("600\n200",baos);

    }

    public Console getConsoleWithBufferedInputAndOutput(String input, ByteArrayOutputStream baos){
        Console console = new Console();
    }

}
