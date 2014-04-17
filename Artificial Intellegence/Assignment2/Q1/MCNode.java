/**
 * Node implementation for the Missionary-Cannibal problem
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;

public class MCNode extends Node
{
   //Constants to represent the various positions
    private static final int M1 = 0;
    private static final int C1 = 1;
    private static final int B1 = 2;
    private static final int M2 = 3;
    private static final int C2 = 4;
    private static final int B2 = 5;
    
    MCNode(State state, int depth, double g, double h, Node parent){
        super(state,depth,g,h,parent);          
    }
    
    public  ArrayList<Node> getSuccessors(){
        ArrayList<Node> succs = new ArrayList<Node>(10);                 
        int [] temp = convertStateToArray(getState());
        
        //Run the five actions and make sure the states are possible:
        //System.out.println("Parent: "+ Arrays.toString(temp));
        movexMyC(temp,2,0,succs);
        movexMyC(temp,0,2,succs);
        movexMyC(temp,1,1,succs);
        movexMyC(temp,1,0,succs);
        movexMyC(temp,0,1,succs);
  
        if (succs.size()==0)
            return null;
        else
            return succs;   
    }
    
    private static int[] convertStateToArray(State current){
        String value = current.getValue();
        int[] mcb = new int[6];
        for (int i=0; i<mcb.length; i++)
            mcb[i] = Character.digit(value.charAt(i), 10);
        return mcb;
    }
    
    private static String convertArrayToString(int [] mcb){
        StringBuffer sb = new StringBuffer();
        for(int i:mcb)
            sb.append(String.valueOf(i));
        return sb.toString();
    }
    
    private void movexMyC(int[] curr, int xM, int yC, ArrayList<Node> succs){
        
        //Find where boat is 
        if (curr[B1]==1) //boat is on left bank
        {
            if(xM > 0 && curr[M1] < xM) //Not enough missionaries to move
                return;
            else if(yC > 0 && curr[C1] < yC) // Not enough cannibals to move
                return;
            else if (curr[M1]-xM > 0 && curr[M1]-xM < curr[C1]-yC) //Constraint on left bank violated
                return;
            else if (curr[M2]+xM > 0 && curr[M2]+xM < curr[C2]+yC) //Constraint on right bank violated
                return;
            else {  //everything okay 
                int[] copy = Arrays.copyOf(curr,6);
                copy[M1] -= xM;
                copy[M2] += xM;
                copy[C1] -= yC;
                copy[C2] += yC;
                copy[B1] = 0;
                copy[B2] = 1;
                State temp = new State(convertArrayToString(copy));
                succs.add(new MCNode(temp, depth+1,g+1,0,this));
            }           
        }
        else {   
            if(xM > 0 && curr[M2] < xM) //Not enough missionaries to move
                return;
            else if(yC > 0 && curr[C2] < yC) // Not enough cannibals to move
                return;
            else if (curr[M2]-xM > 0 && curr[M2]-xM < curr[C2]-yC) //Constraint on right bank violated
                return;
            else if (curr[M1]+xM > 0 && curr[M1]+xM < curr[C1]+yC) //Constraint on left bank violated
                return;
            else { //everything okay 
                int[] copy = Arrays.copyOf(curr,6);
                copy[M2] -= xM;
                copy[M1] += xM;
                copy[C2] -= yC;
                copy[C1] += yC;
                copy[B2] = 0;
                copy[B1] = 1;
                State temp = new State(convertArrayToString(copy));
                succs.add(new MCNode(temp, depth+1,g+1,0,this));
            }           
        }
    }
}

