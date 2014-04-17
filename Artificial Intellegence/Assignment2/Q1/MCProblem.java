/**
 * An implementation of the Missionary-Cannibal problem
 * 
 */
import java.util.ArrayList;
public class MCProblem extends Problem {
    private State  start;    
   
    public MCProblem() {
        start = new State("331000",0);    //start state
    }
    
    public State getStartState() {
        return start;
    }
    
    public  Node createNode(State s, int d, double g, double h, Node p) {
        return new MCNode(s,d,g,h,p);
    }
    
    public  double getStepCost(Node n1, Node n2) {
       return 1;
    }
    
    public double getHeuristicValue(Node n){  //Not implemented
        return 0;
    }
    
    public  boolean isGoalState(State state)
    {
       return (state.getValue().equals("000331")? true:false) ;
    }
   

}
