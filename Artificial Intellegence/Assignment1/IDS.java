/*Sterling Kohel
 * CS 300
 * Assignment 1
 * IDS Algorithm
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class IDS extends SearchAlgorithm{
    private LinkedList<Node> openList;
    private HashSet<String> closedList;

    public IDS(){
        openList = new LinkedList<Node>(); 
        closedList = new HashSet<String>();
    }

    public Stack<Node> search(Problem problem){
        for(int level = 0; level < this.threshold; level++){
            Node currentNode = DFS(level, problem);
            if (currentNode == null){}
            else if(problem.isGoalState(currentNode.getState()))
                return getSolutionPath(currentNode);
        }
        return null;
    }
    
    public Node DFS(int tier, Problem problem){
        State initial = problem.getStartState(); 
        Node initialNode = problem.createNode(initial,0,0,0,null);
        openList.add(initialNode);
        numGenerated = 1;
        
        while (!openList.isEmpty()){
            Node current = openList.removeFirst();
            closedList.add(current.getState().toString());    

            if (problem.isGoalState(current.getState()))
                return current;

            ArrayList<Node> successors = current.getSuccessors();

            if (successors != null){
                for(Node node : successors){
                    node.setDepth(current.getDepth()+1);
                    node.setParent(current);
                    if(node.getDepth() <= tier)
                        openList.addFirst(node );
                    numGenerated++;
                }
            }
        }
        return null;
    }
}

