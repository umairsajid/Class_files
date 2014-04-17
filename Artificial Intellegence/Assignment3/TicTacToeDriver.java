/** a Tic-Tac-Toe tournament between a random player and a Nilsson player */
public class TicTacToeDriver
{
    public static void main(String[] args)
    {
    	if (args[0].equals("t"))
    	    tournament();
    	else
    	    testAlphaBeta();
    }// main method


    /**
     *  runs MINIMAX search on Tic-Tac-Toe for one turn only (see the
     *  playGame method in AlphaBeta.java)
     */
    public static void testAlphaBeta()
    {
    	TicTacToePlayer nilssonPlayer = 
    	    new TicTacToePlayer( 10, true,
    				 new NilssonEvalFunction ());
    	TicTacToePlayer randomPlayer = 
    	    new TicTacToePlayer( 1, false,
    				 new RandomEvalFunction ());
    	TicTacToeGame game;
    	AlphaBeta ab;

    	game = new TicTacToeGame(nilssonPlayer,randomPlayer);
    	ab = new AlphaBeta(game,true);
    }// testAlphaBeta method

    /**
     *  100 games between the two players with each of them starting in half
     *  the games
     */
    public static void tournament()
    {
    	int nilssonWins = 0;
    	int randomWins = 0;
    	int ties = 0;
    
    	TicTacToePlayer nilssonPlayer = 
    	    new TicTacToePlayer( 1, true,
    				 new NilssonEvalFunction ());
    	TicTacToePlayer randomPlayer = 
    	    new TicTacToePlayer( 1, false,
    				 new RandomEvalFunction ());
    	TicTacToeGame game;
    	AlphaBeta ab;
    
    	for(int i=0; i<50; i++)
    	{
    	    game = new TicTacToeGame(nilssonPlayer,randomPlayer);
    	    ab = new AlphaBeta(game,false);
    
    	    if (game.getWinner().equals(Game.MAX))
    		nilssonWins++;
    	    else if (game.getWinner().equals(Game.MIN))
    		randomWins++;
    	    else
    		ties++;
    	}// first set of 50 games with Nilsson going first
    
    	System.out.println( nilssonWins + "/" + randomWins + "/" + ties);
    
    	nilssonWins = randomWins = ties = 0;
    	randomPlayer = 
    	    new TicTacToePlayer( 1, true, // maxPLayer
    				 new RandomEvalFunction ());
    	nilssonPlayer = 
    	    new TicTacToePlayer( 1, false, // minPlayer
    				 new NilssonEvalFunction ());
    
    	for(int i=0; i<50; i++)
    	{
    	    game = new TicTacToeGame(randomPlayer,nilssonPlayer);
    	    ab = new AlphaBeta(game,false);
    
    	    if (game.getWinner().equals(Game.MAX))
    		randomWins++;
    	    else if (game.getWinner().equals(Game.MIN))
    		nilssonWins++;
    	    else
    		ties++;
    	}// second set of 50 games with Random playing first
    
    	System.out.println( randomWins + "/" + nilssonWins + "/" + ties);

    }// tournament method

}// TicTacToeDriver class
