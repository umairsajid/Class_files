/**
 * Super class for specific problems to test; extend this with your specific problem scenario 
 */
public abstract class Problem {

   	public abstract State getStartState();  	
   	
   	//Factory Method to generate the appropriate node for this particular problem
   	public abstract Node createNode(State s, int d, double g, double h, Node p);
   	
 	public abstract double getStepCost(Node n1, Node n2);
 	public abstract double getHeuristicValue(Node n);
 	public abstract boolean isGoalState(State state);	

}
