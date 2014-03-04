/**
 * Sterling Kohel
 * CS 300
 * Assignment 1
 * An implementation of the Shot Glass problem
 */
import java.util.ArrayList;
public class ShotGlassProblem extends Problem {
    private State  start;    
   
    public ShotGlassProblem() {
        start = new State("00",0);    //start state
    }
    
    public State getStartState() {
        return start;
    }
    
    public  Node createNode(State s, int d, double g, double h, Node p) {
        return new ShotGlassNode(s,d,g,h,p);
    }
    
    public  double getStepCost(Node n1, Node n2) {
       return 1;
    }
    
    public double getHeuristicValue(Node n){  //Not implemented
        return 0;
    }
    
    public  boolean isGoalState(State state)
    {
       return (state.getValue().equals("20")? true:false) ;
    }
   

}