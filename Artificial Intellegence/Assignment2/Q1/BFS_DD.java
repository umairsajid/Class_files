/**
 * BFS with duplicate detection implementation algorithm
 * 
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.HashSet;

public class BFS_DD extends SearchAlgorithm {
    private LinkedList<Node> openList;
    private HashSet<String> closedList;

    public BFS_DD(){
        openList = new LinkedList<Node>(); 
        closedList = new HashSet<String>();
    }// constructor
    
    public Stack<Node> search(Problem problem) {
        State initial = problem.getStartState(); 
        Node initialNode = problem.createNode(initial,0,0,0,null);
        openList.add(initialNode);
        numGenerated = 1;
        
        while (!openList.isEmpty()) {
            Node current = openList.removeFirst();
            closedList.add( current.getState().toString() );    
            
            //Many implementations check this on generation to improve  speed a bit
            //but for consistency, we do goal checking on expansion
            if (problem.isGoalState(current.getState()))
                return getSolutionPath(current);
                
            ArrayList<Node> successors = current.getSuccessors();

            if (successors != null) {
                 for(Node node : successors)
                 {
                     if (!closedList.contains(node.getState().toString()))
                     {
                          node.setDepth(current.getDepth()+1);
                          node.setParent(current);
                          openList.add( node );
                     }
                     numGenerated++;
                 }// loop on successors
            }// there is at least one successor
        }// expansion cycle
        
        return null; // no solution found
    }// search method  

}// BFS class
