/** node in the MINIMAX search tree for a game of Tic-Tac-Toe */
public class TicTacToeNode extends Node
{
    /** 3-by-3 Tic-Tac-Toe board */
    private char[][] board;
   
    TicTacToeNode(int depth, String move)
    {
    	super(depth,move);
    	board = new char[][]{ {' ',' ',' '},{' ',' ',' '},{' ',' ',' '} };
    }// (int, String) constructor

    TicTacToeNode(int depth, String move, char[][] board)
    {
    	super(depth,move);
    	this.board = board;
    }// (int,String,char[][]) constructor

    public char[][] getBoard()
    {
    	return board;
    }// getBoard method

    public java.util.ArrayList<Node> getChildren(Player player)
    {

    	char piece = ((TicTacToePlayer)player).getPiece();
    	java.util.ArrayList<Node> children = new java.util.ArrayList<Node>();
    
    	for (int r=0; r<=2; r++)
    	    for (int c=0; c<=2; c++)
    		if (board[r][c]==' ')
    		{
    		    char[][] childBoard = new char[3][3];
    		    for(int r1=0;r1<=2; r1++)
    			for(int c1=0;c1<=2; c1++)
    			    childBoard[r1][c1] = board[r1][c1];
    		    childBoard[r][c] = piece;
    		    children.add( new TicTacToeNode( depth+1, 
    						     "at " + r + "," + c,
    						     childBoard ) );
    		}
    
    	return children;	
    }// getChildren method


    public void print()
    {
    	for(int r=0;r<=2; r++)
    	{
    	    System.out.println("+-+-+-+");
    	    for(int c=0;c<=2; c++)
    		System.out.print("|"+board[r][c]);
    	    System.out.println("|");
    	}
    	System.out.println("+-+-+-+\n");
    }// print method

}// TicTacToeNode class
