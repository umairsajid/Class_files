public class PQGame extends Game
{
	private static String Start;
	public static boolean first = true;
    PQGame(Player maxPlayer, Player minPlayer,String st)
    {
    	super(minPlayer,maxPlayer);
    	Start = st;
    }// (Player,Player) constructor

    public Node getInitialState()
    {
    	if(Start.equals("5")){
    		first = false;
    		PQNode p = new PQNode(0, "", new char[][]{ {'P'},{'P',' ','P'},{'Q'},{' ',' ',' '} });
    		p.setParent(null);
    		return  p;
    	}
    	else if(Start.equals("6")){
    		first = false;
    		PQNode p = new PQNode(0, "", new char[][]{ {'P'},{'P',' ','P'},{' '},{'Q',' ',' '} });
    		p.setParent(null);
    		return  p;
    	}
    	else if(Start.equals("7")){
    		first = false;
    		PQNode p = new PQNode(0, "", new char[][]{ {'P'},{'P',' ','P'},{' '},{' ','Q',' '} });
    		p.setParent(null);
    		return  p;
    	}
    	else{
    		first = false;
    		PQNode p = new PQNode(0, "", new char[][]{ {'P'},{'P',' ','P'},{' '},{' ',' ','Q'} });
    		p.setParent(null);
    		return  p;
    	}
    }// getInitialState method

    public boolean over(Node node)
    {
    	char[][] board = ((PQNode)node).getBoard();
    	if(depthSearch((PQNode)node)){
    		winner = MAX;
			return true;
    	}
    
    	// first row
    	if  (board[0][0] == 'Q')
    	{
    	    winner = MAX;
    	    return true;
    	}
    
    	// second row
    	if (board[3][2] == 'Q'  && board[1][2] == 'P' && board[3][1] == 'P' &&
    	    board[2][0] == 'P')
    	{
    	    winner = MIN;
    	    return true;
    	}
    
    	// third row
    	if (board[3][1] == 'Q'  && board[2][0] == 'P' && board[3][0] == 'P' &&
        	    board[3][2] == 'P')
        {
            winner = MIN;
            return true;
        }
    
    	// first column
    	if (board[3][0] == 'Q'  && board[1][0] == 'P' && board[3][1] == 'P' &&
        	    board[2][0] == 'P')
        {
            winner = MIN;
            return true;
        }  
    	return false;
    }// over method
    
    public boolean depthSearch(PQNode node){
    	int[] occurence = {0,0,0,0,0,0,0};
    	PQNode current = node;
    	int counter = 1;
    	while(current.getParent() !=null){
    		char[][] board = current.getBoard();
    		if(counter % 2 == 1)
    		{
    			if(board[1][0] == 'Q'){
    				occurence[0] += 1;
    			}
    			if(board[1][1] == 'Q'){
    				occurence[1] += 1;
    			}
    			if(board[1][2] == 'Q'){
    				occurence[2] += 1;
    			}
    			if(board[2][0] == 'Q'){
    				occurence[3] += 1;
    			}
    			if(board[3][0] == 'Q'){
    				occurence[4] += 1;
    			}
    			if(board[3][1] == 'Q'){
    				occurence[5] += 1;
    			}
    			if(board[3][2] == 'Q'){
    				occurence[6] += 1;
    			}
    			for(int x : occurence){
    				if(x >= 3)
    					return true;
    			}
    		}
    		counter++;
    		current = (PQNode) current.getParent();
    	}
    	return false;
    }

}// TicTacToeGame class