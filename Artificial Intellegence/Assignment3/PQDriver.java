
public class PQDriver
{
    public static void main(String[] args)
    {
    	if (args[0].equals("t"))
    	    tournament(args[1]);
    	else
    	    testAlphaBeta(args[1]);
    }// main method


    /**
     *  runs MINIMAX search on PQGame for one turn only (see the
     *  playGame method in AlphaBeta.java)
     */
    public static void testAlphaBeta(String start)
    {
    	PQPlayer randPlayer = 
    	    new PQPlayer( 5, true,
    				 new RandomEvalFunction ());
    	PQPlayer randomPlayer = 
    	    new PQPlayer( 5, false,
    				 new RandomEvalFunction ());
    	PQGame game;
    	AlphaBeta ab;

    	game = new PQGame(randPlayer,randomPlayer,start);
    	ab = new AlphaBeta(game,true);
    }// testAlphaBeta method

    /**
     *  100 games between the two players with each of them starting in half
     *  the games
     */
    public static void tournament(String start)
    {
    	int randWins = 0;
    	int randomWins = 0;
    	int ties = 0;
    
    	PQPlayer randPlayer = 
    	    new PQPlayer( 1, true,
    				 new RandomEvalFunction ());
    	PQPlayer randomPlayer = 
    	    new PQPlayer( 1, false,
    				 new RandomEvalFunction ());
    	PQGame game;
    	AlphaBeta ab;
    
    	for(int i=0; i<50; i++)
    	{
    	    game = new PQGame(randPlayer,randomPlayer,start);
    	    ab = new AlphaBeta(game,false);
    
    	    if (game.getWinner().equals(Game.MAX))
    		randWins++;
    	    else if (game.getWinner().equals(Game.MIN))
    		randomWins++;
    	    else
    		ties++;
    	}// first set of 50 games with Nilsson going first
    
    	System.out.println( randWins + "/" + randomWins + "/" + ties);
    
    	randWins = randomWins = ties = 0;
    	randomPlayer = 
    	    new PQPlayer( 1, true, // maxPLayer
    				 new RandomEvalFunction ());
    	randPlayer = 
    	    new PQPlayer( 1, false, // minPlayer
    				 new RandomEvalFunction ());
    
    	for(int i=0; i<50; i++)
    	{
    	    game = new PQGame(randomPlayer,randPlayer,start);
    	    ab = new AlphaBeta(game,false);
    
    	    if (game.getWinner().equals(Game.MAX))
    		randomWins++;
    	    else if (game.getWinner().equals(Game.MIN))
    		randWins++;
    	    else
    		ties++;
    	}// second set of 50 games with Random playing first
    
    	System.out.println( randomWins + "/" + randWins + "/" + ties);

    }// tournament method

}// TicTacToeDriver class
