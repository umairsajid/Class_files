import java.util.*;
//This class is the node that wraps a state(variable) for the chronological backtrack
public class CBNode
{
    protected Variable variable;
    protected int depth;
    protected Value assigned;
    protected CBNode parent;

    public CBNode(Variable variable, Value assigned, int depth, CBNode parent){
        this.variable = variable;
        this.assigned = assigned;
        this.depth = depth;
        this.parent = parent;
    }
    
    //Check if your  value is consistent with  value of another node 
    public boolean isConsistent(CBNode node){
       //Get variable
       Variable other = node.getVariable();
       Variable me = getVariable();
       //Get key for this node:
       String key = me.getId()+"|"+other.getId();
       
       if (me.getOutgoing().containsKey(key)) {//Is there  an NE constraint with this variable?
            if (!assigned.equals(node.getAssigned())) 
                return true; 
            else
                return false;
       }
       return true; //No constraint so always true
    }    
    
    public Variable getVariable() {
        return variable;   
    }

    public Value getAssigned(){
        return assigned;
    }
    
    public void setAssigned(Value d){
        assigned=d;
    }
    
    public int getDepth(){
        return depth;
    }
    
    public void setDepth(int d){
        depth=d;
    }

    public CBNode getParent(){
        return parent;   
    }
    
    public void setParent(CBNode p){
        parent=p;   
    }
}