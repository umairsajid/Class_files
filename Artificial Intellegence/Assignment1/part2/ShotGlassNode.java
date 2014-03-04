/*
 * Sterling Kohel
 * CS 300
 * Assignment 1
 * Node implementation for the Shot Glass problem
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;

public class ShotGlassNode extends Node
{
   //Constants to represent the various positions
    private static final int S1 = 0;//3 oz.
    private static final int S2 = 1;//4 oz.
    
    
    ShotGlassNode(State state, int depth, double g, double h, Node parent){
        super(state,depth,g,h,parent);          
    }
    
    public  ArrayList<Node> getSuccessors(){
        ArrayList<Node> succs = new ArrayList<Node>(10);                 
        int [] temp = convertStateToArray(getState());
        int x = temp[0];
        int y = temp[1];
        //Run the eight actions and make sure the states are possible:
        //System.out.println("Parent: "+ Arrays.toString(temp));

        pourXY(temp,x,4,succs);//#1 Fill 4 oz. sg from bottle
        pourXY(temp,3,y,succs);//#2 Fill 3 oz. sg from bottle
        pourXY(temp,x,0,succs);//#3 Empty 4 oz. sg on ground
        pourXY(temp,0,y,succs);//#4 Empty 3 oz. sg on ground
        pourXY(temp,x-(4-y),4,succs);//#5 Pour 3 oz. into 4 oz. until 4 oz. full
        pourXY(temp,3,y-(3-x),succs);//#6 Pour 4 oz. into 3 oz. until 3 oz. full
        pourXY(temp,0,x+y,succs);//#7 Combine both quantities into 4 0z.
        pourXY(temp,x+y,0,succs);//#8 Combine both quantities into 3 0z.
  
        if (succs.size()==0)
            return null;
        else
            return succs;   
    }
    
    private static int[] convertStateToArray(State current){
        String value = current.getValue();
        int[] mcb = new int[2];
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
    
    private void pourXY(int[] curr, int x, int y, ArrayList<Node> succs){
        if (x < 0 || x > 3)
            return;
        else if (y < 0 || y > 4)
            return;

        int[] copy = Arrays.copyOf(curr,2);
        copy[S1] = x;
        copy[S2] = y;
        State temp = new State(convertArrayToString(copy));
        succs.add(new ShotGlassNode(temp, depth+1,g+1,0,this));
    }
}

