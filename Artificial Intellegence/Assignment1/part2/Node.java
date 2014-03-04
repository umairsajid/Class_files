/**
 * Super class for a node in your problem.
 */
import java.util.ArrayList;

public abstract class Node implements Comparable<Node>{
    protected State state;
    protected int depth;
    protected double g;
    protected double h;
    protected Node parent;
    
    public Node(State state){
         this(state,0,0,0,null);
    }

    Node(State state, int depth, double g, double h, Node parent){
        this.state = state;
        this.depth = depth;
        this.g = g;
        this.h = h;
        this.parent = parent;
    }// (State,int,double,double,Node) constructor
    
    //Abstract method to be implemented by all child 
    public abstract ArrayList<Node> getSuccessors();      
    
    public State getState(){
        return state;   
    }// getState method
    
    public Node getParent(){
        return parent;   
    }// getParent method
    
    public int getDepth(){
        return depth;
    }// getDepth method
    
    public double getG(){
        return g;
    }// getG method
    
    public double getH(){
        return h;
    }// getH method
    
    public void setDepth(int d){
        depth=d;
    }// setDepth method
    
    public void setG(double g){
        this.g = g;   
    }// setG method
    
    public void setH(double h){
        this.h = h;   
    }// setH method
    
    public void setParent(Node p){
        parent=p;   
    }// setParent method
    
    public int compareTo(Node n){
        return 0;
    }
}
