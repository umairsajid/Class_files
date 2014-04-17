import java.util.ArrayList;
public class EightPuzzle extends Problem{
    private State  start;   
    private int heuristic;
   
    public EightPuzzle(String begin, int h){ 
        start = new State(begin);  
        heuristic = h;
    }
    
    public State getStartState(){
        return start;
    }
    
    public  Node createNode(State s, int d, double g, double h, Node p){
        return new EightPuzzleNode(s,d,g,h,p);
    }
    
    public  double getStepCost(Node n1, Node n2){
       return 1;
    }
    
    //Implement this method for Qn 1(b): You may declare helper methods.
    public double getHeuristicValue(Node n){  
        //You will need to grab the state from the node
        if (heuristic==1){
            return 0; //Do heuristic 1 here
        }
        else {
           return 0; //Do heuristic 2 here 
        }
    }
    
    public  boolean isGoalState(State state){
       return (state.getValue().equals("123456780")? true:false) ;
    }
   

}
