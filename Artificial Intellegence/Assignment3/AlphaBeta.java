/** the minimax search algorithm with alpha-beta pruning */
public class AlphaBeta
{
    /** initial value of alpha */
    private static final int MINUS_INFINITY = Integer.MIN_VALUE;

    /** initial value of beta */
    private static final int PLUS_INFINITY = Integer.MAX_VALUE;

    /** game for which the search is performed */
    private Game game;

    /** MAX player in the game */
    private Player maxPlayer;

    /** MIN player in the game */
    private Player minPlayer;

    /** if true, the board is shown after each move */
    private boolean showBoard;

    /** current node, that is, root node of the MINIMAX search tree */
    private Node current;

    /** player whose turn it is to play */
    private Player currentPlayer;

    /** node at depth 1 resulting from the move chosen by the current player */
    private Node next;

    /** number of nodes visited by MINIMAX search */
    private double numVisitedNodes;

    /**
     *  Wrap an alpha-beta instance around a game and play the whole game
     *  @param game game to be played
     *  @param showBoard boolean flag for debugging
     */
    AlphaBeta(Game game, boolean showBoard)
    {
    	this.game = game;
    	this.maxPlayer = game.getMaxPlayer();
    	this.minPlayer = game.getMinPlayer();
    	this.showBoard = showBoard;
    	this.numVisitedNodes = 0;
    	playGame();

    	if (showBoard)
    	    System.out.println( "Number of visited nodes = " + numVisitedNodes);
    }// (Game,boolean) constructor

    /**
     *   Plays the game that this alpha-beta instance is wrapped around
     */
    public void playGame()
    {
    	current = game.getInitialState(); // assuems that game is not over yet
    	numVisitedNodes++;

    	if (showBoard)
    	    current.print();

    	while(true)  // each player plays once during each iteration
    	{
    	    currentPlayer = maxPlayer;      // MAX player goes first
    	    maximin(current,MINUS_INFINITY,PLUS_INFINITY);
    	    current = next;

    	    if (showBoard)
    	    {
    			System.out.println("MAX plays " + current.getMove());
    			current.print();
    	    }

    	    if (game.over(current))
    			break;

    	    currentPlayer = minPlayer;      // MIN player goes next
    	    minimax(current,MINUS_INFINITY,PLUS_INFINITY);
    	    current = next;

    	    if (showBoard)
    	    {
    			System.out.println("MIN plays " + current.getMove());
    			current.print();
    	    }

    	    if (game.over(current))
    			break;

	}// main game loop

    }//playGame method

    /**
     *  Minimax search with alpha-beta pruning from the perspective of
     *  the MAX player
     *
     *  @param node current node in the minimax search tree
     *  @param alpha lower bound on the value of the best node for MAX
     *  @param beta  upper bound on the value of the best node for MIN
     *  @return the value of the current node according to MINIMAX search
     */
    public double maximin(Node node, double alpha, double beta)
    {
    	if (game.over(node) ||  // is the current node a leaf node?
    	    (node.getDepth()-current.getDepth() == currentPlayer.getMaxDepth()))
    	    return currentPlayer.computeValue(node);

    	Node best = null; // best child: assign it the correct value
    	double bestValue = MINUS_INFINITY;

    	for( Node child : node.getChildren( maxPlayer ) )
    	{
    	    numVisitedNodes++; // must look at this child

    	    double value = minimax( child, alpha, beta);
    	    alpha = Math.max(alpha, value);
    	    best = child;
    	    if (beta <= alpha) // find the best child and its value
    	    {
    			break;
    	    }
    	}
    	next = best; // perform the move

    	return alpha;

    }// maximin method

    /**
     *  Minimax search with alpha-beta pruning from the perspective of
     *  the MIN player
     *
     *  @param node current node in the minimax search tree
     *  @param alpha lower bound on the value of the best node for MAX
     *  @param beta  upper bound on the value of the best node for MIN
     *  @return the value of the current node according to MINIMAX search
     */
    public double minimax(Node node, double alpha, double beta)
    {
    	if (game.over(node) || // is the current node a leaf node?
    	    (node.getDepth()-current.getDepth() == currentPlayer.getMaxDepth()))
    	    return currentPlayer.computeValue(node);

    	Node best = null; // best child: assign it the correct value
    	double bestValue = PLUS_INFINITY;

    	for( Node child : node.getChildren( minPlayer ) )
    	{
    	    numVisitedNodes++; // must look at this child

    	    double value = maximin( child, alpha, beta);
    	    beta = Math.min(beta, value);
			best = child;

    	    if (beta <= alpha) // find the best child and its value
    	    {
    			break;
    	    }
    	}
    	next = best;  // perform the move

    	return beta;

    }// minimax method

}// AlphaBeta class
