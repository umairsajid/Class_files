
public class PQPlayer extends Player
{
    PQPlayer( int maxDepth, boolean isMax, EvalFunction fn )
    {
    	super(maxDepth, isMax, fn);
    }// (int,boolean,EvalFunction) constructor

    /** note that 'Q' is the MAX player */
    public char getPiece()
    {
    	return (isMax ? 'Q' : 'P');
    }// getPiece method

}// PQPlayer class
