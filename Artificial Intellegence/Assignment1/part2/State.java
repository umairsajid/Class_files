/**
 * The class that models a generic state.
 */

public class State {
    protected String value;
    protected int id;
    
    public State(String s){
         this(s,0);
    }
    
    public State(String s, int i){
        value = s;
        id = i;
    }
    
    public String toString(){  return value;}
    
    public String getValue(){ return value;}
    
    public int getID(){ return id;}
    
    public boolean equals(State state){
        return value.equals(state);
    }
    
    
}
