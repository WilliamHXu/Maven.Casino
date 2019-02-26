package io.zipcoder.casino.Craps;

import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Player;

public class CrapsPlayer extends Player {

    private Console inOut = new Console(System.in, System.out);

    public void CrapsPlayer() {

    }

    public CrapsPlayer(Player player) {
        super(player.getName(), player.getWallet());
        super.playerNetGainLoss = 0;
    }


    public void bet(Integer value) {
    }

    /**
     * get method to return Craps player wallet balance
     */
    public Integer getWalletBalanceCrapsPlayer() {
        return this.getWallet();
    }

    /**
     * get method to return Craps player name
     */
    public String getCrapsPlayerName() {
        return this.playerName;
    }

    /**
     * update Craps player wallet, playerwallet and update netgainloss with the winning amount
     */
    public void setPlayerWalletBalance(Integer walletChangeAmt) {

        this.updateWallet(walletChangeAmt);
    }

    public Integer getNetGainLossPlayerCraps() {
        return this.getPlayerNetGainLoss();
    }

    public void setNetGainLossPlayerCraps(Integer netGainLossPlayerCraps) {
        this.playerNetGainLoss += netGainLossPlayerCraps;
    }

    public void setCrapsPlayer(String playerName) {
        this.playerName = playerName;
    }
}


