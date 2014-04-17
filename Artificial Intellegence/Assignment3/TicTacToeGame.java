/** a Tic-Tac-Toe game */
public class TicTacToeGame extends Game
{
    TicTacToeGame(Player maxPlayer, Player minPlayer)
    {
    	super(minPlayer,maxPlayer);
    }// (Player,Player) constructor

    public Node getInitialState()
    {
    	return  new TicTacToeNode(0, "",
                    new char[][]{ {' ',' ',' '},{' ',' ',' '},{' ',' ',' '} });
    }// getInitialState method

    public boolean over(Node node)
    {
    	char[][] board = ((TicTacToeNode)node).getBoard();
    
    	// first row
    	if  (board[0][0] != ' '         &&
    	     board[0][0] == board[0][1] &&
    	     board[0][1] == board[0][2])
    	{
    	    winner = (board[0][0]=='X' ? MAX : MIN);
    	    return true;
    	}
    
    	// second row
    	if (board[1][0] != ' '         &&
                board[1][0] == board[1][1] &&
    	    board[1][1] == board[1][2])
    	{
    	    winner = (board[1][0]=='X' ? MAX : MIN);
    	    return true;
    	}
    
    	// third row
    	if (board[2][0] != ' '         &&
                board[2][0] == board[2][1] &&
    	    board[2][1] == board[2][2])
    	{
    	    winner = (board[2][0]=='X' ? MAX : MIN);
    	    return true;
    	}  
    
    	// first column
    	if (board[0][0] != ' '         &&
                board[0][0] == board[1][0] &&
    	    board[1][0] == board[2][0])
    	{
    	    winner = (board[0][0]=='X' ? MAX : MIN);
    	    return true;
    	}  
    
    	// second column
    	if (board[0][1] != ' '         &&
                board[0][1] == board[1][1] &&
    	    board[1][1] == board[2][1])
    	{
    	    winner = (board[0][1]=='X' ? MAX : MIN);
    	    return true;
    	}  
    
    	// third column
    	if (board[0][2] != ' '         &&
                board[0][2] == board[1][2] &&
    	    board[1][2] == board[2][2])
    	{
    	    winner = (board[0][2]=='X' ? MAX : MIN);
    	    return true;
    	}  
    
    	// main diagonal
    	if (board[0][0] != ' '         &&
    	    board[0][0] == board[1][1] &&
    	    board[1][1] == board[2][2])
    	{
    	    winner = (board[0][0]=='X' ? MAX : MIN);
    	    return true;
    	}  
    
    	// other diagonal
    	if (board[2][0] != ' '         &&
    	    board[2][0] == board[1][1] &&
    	    board[1][1] == board[0][2])
    	{
    	    winner = (board[2][0]=='X' ? MAX : MIN);
    	    return true;
    	}  
           
    	boolean full = true;
    	for(int r=0; r<=2; r++)
    	    for(int c=0; c<=2; c++)
    		if (board[r][c] == ' ')
    		    full = false;
    	if (full)
    	{
    	    winner = TIE;
    	    return true;
    	}  
    	else
    	    return false;
    }// over method

}// TicTacToeGame class
