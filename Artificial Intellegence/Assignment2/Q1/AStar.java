
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.HashSet;
import java.util.Comparator;


public class AStar extends SearchAlgorithm {
    private PriorityQueue<Node> openList;
    private Comparator<Node> comp = new Compare();
    private HashSet<String> closedList;
    

    public AStar(){
        openList = new PriorityQueue<Node>(2,comp); 
        closedList = new HashSet<String>();
    }// constructor
    
    public Stack<Node> search(Problem problem) {
        State initial = problem.getStartState(); 
        Node initialNode = problem.createNode(initial,0,0,0,null);
        initialNode.setH(problem.getHeuristicValue(initialNode));
        openList.add(initialNode);
        numGenerated = 1;
        
        while (!openList.isEmpty()) {
            Node current = openList.poll();
            closedList.add( current.getState().toString() );    
            
            //Many implementations check this on generation to improve  speed a bit
            //but for consistency, we do goal checking on expansion
            if (problem.isGoalState(current.getState()))
                return getSolutionPath(current);
                
            ArrayList<Node> successors = current.getSuccessors();

            if (successors != null) {
                 for(Node node : successors)
                 {
                	 node.setH(problem.getHeuristicValue(node));
                     if (!closedList.contains(node.getState().toString()))
                     {
                          node.setDepth(current.getDepth()+1);
                          node.setParent(current);
                          if(!openList.contains(node)){
                        	  openList.add( node );
                          }
                          else{
                        	  java.util.Iterator<Node> it =  openList.iterator();
                        	  while(it.hasNext()){
                        		  Node temp = it.next();
                        		  temp.setH(problem.getHeuristicValue(temp));
                        		  if((node.getState().toString()).equals(temp.getState().toString())){
                        			  if((node.getH() + node.getG()) < (temp.getH() + temp.getG())){
                        				  openList.remove(temp);
                        				  openList.add(node);
                        			  }
                        			  else{
                        				  break;
                        			  }
                        		  }
                        	  }
                        		  
                          }
                     }
                     numGenerated++;
                 }// loop on successors
            }// there is at least one successor
        }// expansion cycle
        
        return null; // no solution found
    }// search method  

}// AStar class



class Compare implements Comparator<Node>
{
    @Override
    public int compare(Node x, Node y)
    {
        // Compares nodes based on their heuristic + actual graph value
        // ones with less get put in front in priority queue
        if ((x.getG()+x.getH()) < (y.getG()+y.getH()))
        {
            return -1;
        }
        if ((x.getG()+x.getH()) > (y.getG()+y.getH()))
        {
            return 1;
        }
        return 0;
    }
}
