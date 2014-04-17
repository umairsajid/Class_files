/** Represents a variable in a SudokuProblem */
import java.util.*;
public class Variable
{
    private int id;   // Unique ID for variable
    private int i,j; //i,j positions
    private Set<Value> domain; // set of values (1 up) that the variable can take
    private Value assigned; //The value this cell finally takes
    private HashMap<String,Arc> incoming; // incoming arcs
    private HashMap<String,Arc> outgoing; // outgoing arcs

   //This constructor enforces unary constraints directly
    public Variable(int id, int i, int j, Value given) {
        this(id,i,j);
        domain = new HashSet<Value>();
        assigned = given;
        domain.add(assigned);  //domain contains only one value
    }
    
    public Variable(int id, int i, int j) {
        this.id = id; //unique ID for variable, used for hashmap key
        this.i=i;
        this.j=j;
        domain = new HashSet<Value>();
        for(int idx=1; idx<=9; idx++)
            domain.add(new Value(idx));  
        assigned = null;
        incoming = new HashMap<String,Arc>();
        outgoing = new HashMap<String,Arc>();
    }

    public String toString(){
        return "["+i+","+j+"("+id+")" + "=" + assigned.toString() + "]";
    }
      
    public Value getValue() {
        return assigned;
    }
    
    public void setValue(Value v){  
            assigned = v;
    }

    public boolean equals(Object obj){
        if (obj instanceof Variable){
            Variable other =(Variable)obj;
            if (this.getI()==other.getI() && this.getJ()==other.getJ())
                return true;
        }
        return false;
    }
    
    public int getId(){ return id; }

    public int getI(){  return i;}
    
    public int getJ(){ return j; }

    public Collection<Value> getDomain(){ return domain;}
    
    public void addOutgoing(Arc arc) {
        String key = arc.getSource().getId()+"|"+arc.getDestination().getId();
        outgoing.put(key, arc);
    }

    public void addIncoming(Arc arc) {
        String key = arc.getSource().getId()+"|"+arc.getDestination().getId();
        incoming.put(key, arc);
    }

    public HashMap<String,Arc> getIncoming() {
        return incoming;
    }

    public HashMap<String,Arc> getOutgoing() {
        return outgoing;
    }

    //Method for use in AC3(); Makes a variable domain consistent with that of the other variable
    //Returns true if the domain was modified in any way.
    public boolean makeConsistent(Variable other){              
        ArrayList<Value> toDelete=new ArrayList<Value>();
        ArrayList<Value> dest = new ArrayList<Value>(other.getDomain());
        
        //Iterate through all values in domain and check if any values are inconsistent
        for (Iterator<Value> src = domain.iterator(); src.hasNext();){
            Value v = src.next();
            boolean satisfiable=false;
            for (int idx=0;idx<dest.size(); idx++){
                if (!v.equals(dest.get(idx))){
                    satisfiable = true;
                    break;
                }
            }
            if (!satisfiable) //If so, mark them for deletion
                toDelete.add(v);                
        }
        
        //Then walk through all values marked and delete them
        for (int idx=0;idx<toDelete.size(); idx++)
            domain.remove(toDelete.get(idx));
        
        //if nothing deleted, return false, else return true
        if (toDelete.size()>0)
            return true;
        else 
            return false;
        
    }
    
 
    //This is the expand method used in chronological backtracking   
    public Collection<CBNode> expand(int depth,CBNode parent) {
        //Create one node for every domain value and add it to the list
        ArrayList<CBNode> expanded = new ArrayList<CBNode>();
        for (Iterator<Value> set = domain.iterator(); set.hasNext();){
            Value v = set.next();
            CBNode node = new CBNode(this,v,depth,parent);
            expanded.add(node);
        } 
       return expanded; 
    }
}// Variable class