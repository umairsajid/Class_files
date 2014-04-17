/** Represent a two-player, perfect-information, zero-sum game */
public abstract class Game
{
    // These constants are used for end-of-game message
    public static final String MAX = "MAX";
    public static final String MIN = "MIN";
    public static final String TIE = "neither MAX nor Min";

    /** player that goes first in the game */
    protected Player maxPlayer;

    /** player that goes second in the game */
    protected Player minPlayer;

    /** winner of the game, if any */
    protected String winner;

    /** constructor */
    Game(Player minPlayer, Player maxPlayer)
    {
    	this.minPlayer = minPlayer;
    	this.maxPlayer = maxPlayer;
    	winner = "Game is NOT over yet";
    }// (Player,Player) constructor

    public String getWinner()
    {
    	return winner;
    }// getWinner method

    public Player getMinPlayer()
    {
    	return minPlayer;
    }// getMinPlayer method

    public Player getMaxPlayer()
    {
    	return maxPlayer;
    }// getMaxPlayer method

    /** return the node representing the initial configuration of the game */
    public abstract Node getInitialState();

    /** return true iff the node represents an ending configuration of the game */
    public abstract boolean over(Node node);

}// Game class
