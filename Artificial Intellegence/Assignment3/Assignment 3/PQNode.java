import java.util.ArrayList;


public class PQNode extends Node
{
    /** 3-by-3 Tic-Tac-Toe board */
    private char[][] board;
    public Node parent = null;
   
    PQNode(int depth, String move)
    {
    	super(depth,move);
    	if(move.equals("5"))
    		board = new char[][]{ {'P'},{'P',' ','P'},{'Q'},{' ',' ',' '} };
    	else if(move.equals("6"))
    		board = new char[][]{ {'P'},{'P',' ','P'},{' '},{'Q',' ',' '} };
    	else if(move.equals("7"))
    		board = new char[][]{ {'P'},{'P',' ','P'},{' '},{' ','Q',' '} };
    	else if(move.equals("8"))
    		board = new char[][]{ {'P'},{'P',' ','P'},{' '},{' ',' ','Q'} };
    	
    }// (int, String) constructor

    PQNode(int depth, String move, char[][] board)
    {
    	super(depth,move);
    	this.board = board;
    }// (int,String,char[][]) constructor

    public void setParent(Node p){
    	parent = p;
    }
    
    public Node getParent(){
    	return parent;
    }
    
    public char[][] getBoard()
    {
    	return board;
    }// getBoard method

    
    public char[][] CreateCopy(char[][] Orig)
    {
    	char[][] copy = new char[4][3];
    	for(int i=0; i<4; i++)
    	{
    		for(int j=0; j<3; j++)
    		{
    			if(i == 0 || i == 2){
    				copy[i][j] = Orig[i][j];
    				break;
    			}
    			else
    			{
    				copy[i][j] = Orig[i][j];
    			}
    		}
    	}
    	return copy;
    }
    
    //NOT FINISHED!!!!
    public ArrayList<Node> getChildren(Player player)
    {

    	char piece = ((PQPlayer)player).getPiece();
    	ArrayList<Node> children = new ArrayList<Node>();
    	if(piece == 'P')
    	{
    		if (board[0][0] == 'P')
    		{
    			if(board[1][1] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[1][1] = 'P';
    				childBoard[0][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 1 to 4" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			
    		}
    		if (board[1][0]=='P')
    		{
    			if(board[3][0] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][0] = 'P';
    				childBoard[1][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 2 to 6" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[1][1] == ' '){
    				char[][] childBoard = CreateCopy(board);
    				childBoard[1][1] = 'P';
    				childBoard[1][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 2 to 4" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			
    	    }
    		if(board[1][1] =='P')
    		{
    			if(board[1][0] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[1][0] = 'P';
    				childBoard[1][1] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 4 to 2" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[1][2] == ' '){
    				char[][] childBoard = CreateCopy(board);
    				childBoard[1][2] = 'P';
    				childBoard[1][1] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 4 to 3" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[2][0] == ' '){
    				char[][] childBoard = CreateCopy(board);
    				childBoard[2][0] = 'P';
    				childBoard[1][1] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 4 to 5" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    		}
    		if (board[1][2]=='P')
    		{
    			if(board[3][2] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][2] = 'P';
    				childBoard[1][2] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 3 to 6" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[1][1] == ' '){
    				char[][] childBoard = CreateCopy(board);
    				childBoard[1][1] = 'P';
    				childBoard[1][2] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 3 to 4" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			
    	    }
    		if (board[2][0]=='P')
    		{
    			if(board[3][1] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][1] = 'P';
    				childBoard[2][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 5 to 7" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    	    }
    		if (board[3][0]=='P')
    		{
    			if(board[3][1] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][1] = 'P';
    				childBoard[3][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 6 to 7" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    	    }
    		if (board[3][1]=='P')
    		{
    			if(board[3][0] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][0] = 'P';
    				childBoard[3][1] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 7 to 6" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[3][2] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][2] = 'P';
    				childBoard[3][1] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 7 to 8" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    	    }
    		if (board[3][2]=='P')
    		{
    			if(board[3][1] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][1] = 'P';
    				childBoard[3][2] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 8 to 7" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    	    }
    	}
    	else
    	{
    		if (board[1][0]=='Q')
    		{
    			if(board[3][0] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][0] = 'Q';
    				childBoard[1][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 2 to 6" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[1][1] == ' '){
    				char[][] childBoard = CreateCopy(board);
    				childBoard[1][1] = 'Q';
    				childBoard[1][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 2 to 4" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[0][0] == ' '){
    				char[][] childBoard = CreateCopy(board);
    				childBoard[0][0] = 'Q';
    				childBoard[1][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 2 to 1" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[2][0] == ' '){
    				char[][] childBoard = CreateCopy(board);
    				childBoard[2][0] = 'Q';
    				childBoard[1][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 2 to 5" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			
    	    }
    		if(board[1][1] =='Q')
    		{
    			if(board[1][0] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[1][0] = 'Q';
    				childBoard[1][1] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 4 to 2" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[1][2] == ' '){
    				char[][] childBoard = CreateCopy(board);
    				childBoard[1][2] = 'Q';
    				childBoard[1][1] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 4 to 3" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[2][0] == ' '){
    				char[][] childBoard = CreateCopy(board);
    				childBoard[2][0] = 'Q';
    				childBoard[1][1] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 4 to 5" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[0][0] == ' '){
    				char[][] childBoard = CreateCopy(board);
    				childBoard[0][0] = 'Q';
    				childBoard[1][1] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 4 to 1" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    		}
    		if (board[1][2]=='Q')
    		{
    			if(board[3][2] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][2] = 'Q';
    				childBoard[1][2] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 3 to 6" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[1][1] == ' '){
    				char[][] childBoard = CreateCopy(board);
    				childBoard[1][1] = 'Q';
    				childBoard[1][2] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 3 to 4" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[0][0] == ' '){
    				char[][] childBoard = CreateCopy(board);
    				childBoard[0][0] = 'Q';
    				childBoard[1][2] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 3 to 1" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[2][0] == ' '){
    				char[][] childBoard = CreateCopy(board);
    				childBoard[2][0] = 'Q';
    				childBoard[1][2] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 3 to 5" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			
    	    }
    		if (board[2][0]=='Q')
    		{
    			if(board[3][1] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][1] = 'Q';
    				childBoard[2][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 5 to 7" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[3][0] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][0] = 'Q';
    				childBoard[2][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 5 to 6" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[3][2] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][2] = 'Q';
    				childBoard[2][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 5 to 8" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[1][0] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[1][0] = 'Q';
    				childBoard[2][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 5 to 2" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[1][1] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[1][1] = 'Q';
    				childBoard[2][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 5 to 4" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[1][0] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[1][0] = 'Q';
    				childBoard[2][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 5 to 3" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    	    }
    		if (board[3][0]=='Q')
    		{
    			if(board[3][1] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][1] = 'Q';
    				childBoard[3][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 6 to 7" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[1][0] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[1][0] = 'Q';
    				childBoard[3][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 6 to 2" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[2][0] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[2][0] = 'Q';
    				childBoard[3][0] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 6 to 5" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    	    }
    		if (board[3][1]=='Q')
    		{
    			if(board[3][0] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][0] = 'Q';
    				childBoard[3][1] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 7 to 6" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[3][2] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][2] = 'Q';
    				childBoard[3][1] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 7 to 8" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[2][0] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[2][0] = 'Q';
    				childBoard[3][1] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 7 to 5" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    	    }
    		if (board[3][2]=='Q')
    		{
    			if(board[3][1] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[3][1] = 'Q';
    				childBoard[3][2] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 8 to 7" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[1][2] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[1][2] = 'Q';
    				childBoard[3][2] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 8 to 3" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    			if(board[2][0] == ' ')
    			{
    				char[][] childBoard = CreateCopy(board);
    				childBoard[2][0] = 'Q';
    				childBoard[3][2] = ' ';
    				PQNode c =  new PQNode( depth+1,"From 8 to 5" , childBoard );
    				c.setParent(this);
    				children.add(c);
    			}
    	    }
    	}
    	return children;	
    }// getChildren method


    public void print()
    {
    	System.out.println("  " + board[0][0]);
    	System.out.println("/ | \\");
    	System.out.println(board[1][0] + " " + board[1][1] + " " + board[1][2]);
    	System.out.println("|\\|/|");
    	System.out.println("| "+ board[2][0] + " |");
    	System.out.println("|/|\\|");
    	System.out.println(board[3][0] + " " + board[3][1] + " " + board[3][2]);
    }// print method

}// TicTacToeNode class
