package io.zipcoder.casino.ChoHan;


import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Player;

import java.util.*;

public class ChoHanPlayer extends Player{
    /**
     * instance variables
     */

    private Console inOut = new Console(System.in, System.out);

    /**Nullary constructor*/
   public void ChoHanPlayer() {

    }

    /** ChoHan player constructor */
    public ChoHanPlayer(Player player) {
        super(player.getName(), player.getWallet());
        super.playerNetGainLoss = 0;
    }

    public void bet (Integer value)
    {}

    /** get method to return ChoHan player wallet balance */
    public Integer getWalletBalanceChoHanPlayer() {
        return this.getWallet();
    }

    /**
     * get method to return ChoHan player name
     */
    public String getCHPlayerName(){
        return this.playerName;
    }

    /**update CH player wallet, playerwallet and update netgainloss with the winning amount*/
    public void setPlayerWalletBalance( Integer walletChangeAmt) {

        this.updateWallet(walletChangeAmt);
    }

    public Integer getNetGainLossPlayerCH() {
        return this.getPlayerNetGainLoss();
    }

    public void setNetGainLossPlayerCH(Integer netGainLossPlayerCH) {
        this.playerNetGainLoss += netGainLossPlayerCH;
    }

    public void setCHPlayer(String playerName)
    {
        this.playerName = playerName;
    }

}