/**
 * UCS  algorithm
 */
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.HashSet;

public class UCS extends SearchAlgorithm {
    private PriorityQueue<Node> openList = new PriorityQueue<Node>();
    private HashSet<String> closedList;

    public UCS(){
        openList = new PriorityQueue<Node>();
        closedList = new HashSet<String>();
    }// constructor
    
    public Stack<Node> search(Problem problem) {
        State initial = problem.getStartState(); 
        Node initialNode = problem.createNode(initial,0,0,0,null);
        openList.offer(initialNode);
        numGenerated = 1;
        
        while (!openList.isEmpty()) {
            Node current = openList.poll();
            closedList.add( current.getState().toString() );    
            
            //For UCS we must do goal checking only on expansion
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
                          openList.offer( node );
                     }
                     numGenerated++;
                 }// loop on successors
            }// there is at least one successor
        }// expansion cycle
        
        return null; // no solution found
    }// search method  

}// BFS class
