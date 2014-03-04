/**
 * Super class for specific algorithms to use; extend this with your specific algorithm
 * 
 */
import java.util.Stack;
public abstract class SearchAlgorithm {
    protected long numGenerated;
    protected int threshold;
    
    public SearchAlgorithm(){
      threshold = Integer.MAX_VALUE;   
    }
    
    //Implement this method in your child classes
    public abstract Stack<Node> search(Problem problem);
    
    public int getThreshold() {
        return threshold;
    }// getThreshold method
    
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }// setThreshold method
    
    public long getNumGenerated() {
        return numGenerated;   
    }// getNumGenerated method

    protected Stack<Node> getSolutionPath(Node node) {
        Stack<Node> stack = new Stack<Node>();
        stack.push( node );
        node = node.getParent();
        while (node != null)
        {
                stack.push(node);
                node = node.getParent();
        }
        return stack;
    }// getSolutionPath method
}
