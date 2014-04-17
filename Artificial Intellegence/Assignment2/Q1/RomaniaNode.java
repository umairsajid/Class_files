/**
 * Node implementation for the Romania map problem
 * 
 */
import java.util.ArrayList;
import java.util.HashSet;

public class RomaniaNode extends Node {
    private RomaniaProblem pr;
    public RomaniaNode(RomaniaProblem pr, State state, int depth, double g, double h, Node parent) {
        super(state,depth,g,h,parent);
        this.pr = pr;
    }
    
    public  ArrayList<Node> getSuccessors() {
        return pr.getNodeSuccessors(this);
    }

    public int compareTo(Node n){
        return (int)(this.g - n.g);
    }
}
