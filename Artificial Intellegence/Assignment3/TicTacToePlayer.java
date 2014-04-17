/** a TicTacToe player who is either the 'X' or the 'O' player */
public class TicTacToePlayer extends Player
{
    TicTacToePlayer( int maxDepth, boolean isMax, EvalFunction fn )
    {
    	super(maxDepth, isMax, fn);
    }// (int,boolean,EvalFunction) constructor

    /** note that 'X' is the MAX player */
    public char getPiece()
    {
    	return (isMax ? 'X' : 'O');
    }// getPiece method

}// TicTacToePlayer class
