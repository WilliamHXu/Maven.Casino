package io.zipcoder.casino.utilities;

public class Player {
    // Instance Variables
    protected String playerName;
    protected Integer walletBalance;
    protected Integer playerNetGainLoss;

    // Constructors

    public Player(String playerName, Integer walletBalance) {
        this.playerName = playerName;
        this.walletBalance = walletBalance;
        this.playerNetGainLoss = 0;
    }

    public Player(String playerName) {
        this(playerName, 10000);
    }

    // Methods

    /**
     * Returns the amount player has left in wallet
     * @return
     */

    public Integer getWallet() {
        return this.walletBalance;
    }

    /**
     * Updates the player wallet and netGainLoss
     * @param walletGainLoss
     */

    public void updateWallet(Integer walletGainLoss) {
        this.walletBalance += walletGainLoss;
        updatePlayerNetGainLoss(walletGainLoss);
    }

    /**
     * Inserts money into the player's wallet
     * @param withdraw
     */

    public void goToATM(Integer withdraw) {
        this.walletBalance += withdraw;
    }

    /**
     * Gets the player's netGainLoss on the day
     * @return
     */

    public Integer getPlayerNetGainLoss() {
        return this.playerNetGainLoss;
    }

    /**
     * USE SPARINGLY
     * Updates the player's newGainLoss on the day.
     * @param netGainLoss
     */

    public void updatePlayerNetGainLoss(Integer netGainLoss) {
        this.playerNetGainLoss += netGainLoss;
    }

    /**
     * Gets the player's name
     * @return
     */

    public String getName() {
        return this.playerName;
    }

    /**
     * Sets the player's name
     * @param playerName
     */

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
