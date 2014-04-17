//Should really be an interface or abstract class
//Captures the values on which we work; ints for sudoku
public class Value {   
    private int value;
    
    public Value(int value){
        this.value=value;
    }

    public int getValue(){return value;}
    
    public void setValue(int value){this.value=value;}
    
    public boolean equals(Object obj){
        if (obj instanceof Value){
            Value other =(Value)obj;
            if (this.getValue()==other.getValue())
                return true;
        }
        return false;
    }
   
    public String toString(){
        return String.valueOf(value);
    }
}