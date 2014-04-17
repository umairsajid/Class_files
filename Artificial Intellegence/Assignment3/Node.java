/** a node in a MINIMAX search tree */
public abstract class Node
{
    /** depth of the node in the MINIMAX search tree */
    protected int depth;

    /** String description of the move leading to this node */
    public String move;

    /** constructor */
    Node(int depth, String move)
    {
    	this.depth = depth;
    	this.move = move;
    }// (int,String) constructor

    public int getDepth()
    {
    	return depth;
    }// getDepth method

    /** Returns a string describing the move that led to this node */
    public String getMove()
    {
    	return move;
    }// getMove method

    /** returns the list of all successors of this node for player */
    public abstract java.util.ArrayList<Node> getChildren(Player player);

    /** sends a description of the node to standard output */
    public abstract void print();

}// Node class
