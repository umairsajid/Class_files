/** a player in a two-player game */
public abstract class Player
{
    /** the maximum number of plies for MINIMAX search */
    protected int maxDepth;

    /** true iff this playe is the MAX player */
    protected boolean isMax;

    /** the static evaluation function used by the player */
    protected EvalFunction fn;

    /** number of games played by this player in a tournament */
    int played;

    /** number of games won by this player in a tournament */
    int won;

    /** number of games lost by this player in a tournament */
    int lost;


    /** Constructor */
    Player( int maxDepth, boolean isMax, EvalFunction fn)
    {
		this.maxDepth = maxDepth;
		this.isMax = isMax;
		this.fn = fn;
		played = won = lost = 0;
    }// (int,boolean,EvalFunction) constructor

    /**
     *  Increment the number of games won
     *  @param n the number of games to be added to the WON counter
     */
    public void incrWon(int n)
    {
		won += n;
    }// incrWon method

    /**
     *  Increment the number of games lost
     *  @param n the number of games to be added to the LOST counter
     */
    public void incrLost(int n)
    {
		lost += n;
    }// incrLost method

    /**
     *  Increment the number of games played
     *  @param n the number of games to be added to the PLAYED counter
     */
    public void incrPlayed(int n)
    {
		played += n;
    }// incrPlayed method

    /** return the number of games played */
    public int getPlayed()
    {
		return played;
    }// getPlayed method

    /** return the number of games won */
    public int getWon()
    {
		return won;
    }// getWon method

    /** return the number of games lost */
    public int getLost()
    {
		return lost;
    }// getLost method

    /** return the maximum depth */
    public int getMaxDepth()
    {
		return maxDepth;
    }// getMaxDepth method

    /** return true iff the player is the MAX player */
    public boolean isMaxPlayer()
    {
		return isMax;
    }// isMaxPlayer method

    /** return the value of the given node for this player */
    public double computeValue(Node node)
    {
		return fn.computeValue(node);  // delegation
    }// computeValue method

}// Player class
