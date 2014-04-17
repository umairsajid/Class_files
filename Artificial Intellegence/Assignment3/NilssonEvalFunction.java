/** compute Nilsson's evaluation function for Tic-Tac-Toe */
public class NilssonEvalFunction extends EvalFunction
{
    private static final char[] piece = {'X','O'};    

    public double computeValue(Node node)
    {
    	char[][] board = ((TicTacToeNode) node).getBoard();
    	int[] value = {0,0};
    	for(int i=0; i<=1; i++)
    	{
    	    // first row
    	    if (board[0][0] == piece[i] ||
    		board[0][1] == piece[i] ||
    		board[0][2] == piece[i] )
    		value[i]++;
    
    	    // second row
    	    if (board[1][0] == piece[i] ||
    		board[1][1] == piece[i] ||
    		board[1][2] == piece[i] )
    		value[i]++;
    
    	    // third row
    	    if (board[2][0] == piece[i] ||
    		board[2][1] == piece[i] ||
    		board[2][2] == piece[i] )
    		value[i]++;
    
    	    // first column
    	    if (board[0][0] == piece[i] ||
    		board[1][0] == piece[i] ||
    		board[2][0] == piece[i] )
    		value[i]++;
    
    	    // second column
    	    if (board[0][1] == piece[i] ||
    		board[1][1] == piece[i] ||
    		board[2][1] == piece[i] )
    		value[i]++;
    
    	    // third column
    	    if (board[0][2] == piece[i] ||
    		board[1][2] == piece[i] ||
    		board[2][2] == piece[i] )
    		value[i]++;
    
    	    // main diagonal
    	    if (board[0][0] == piece[i] ||
    		board[1][1] == piece[i] ||
    		board[2][2] == piece[i] )
    		value[i]++;
    
    	    // other diagonal
    	    if (board[2][0] == piece[i] ||
    		board[1][1] == piece[i] ||
    		board[0][2] == piece[i] )
    		value[i]++;
    	}
    
    	return value[0]-value[1];

    }// computeValue method

}// NilssonEvalFunction class
