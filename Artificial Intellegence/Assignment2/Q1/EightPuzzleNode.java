import java.util.ArrayList;
import java.util.Arrays;

public class EightPuzzleNode extends Node{   
    public EightPuzzleNode(State state, int depth, double g, double h, Node parent){
        super(state,depth,g,h,parent);
    }
    
    public  ArrayList<Node> getSuccessors(){
        ArrayList<Node> succs = new ArrayList<Node>(10);                 
        int [][] temp = convertStateToArray(getState());
        
        //find location of 0
        int blankI, blankJ; //to remember location of blank for successor generation
        int idx = (getState().getValue()).indexOf("0");
        blankI = idx/3;
        blankJ = idx%3;
        
        //Run the four actions:
        //System.out.println("Parent: "+ Arrays.toString(temp));
        attemptMove(temp,-1,0,blankI,blankJ,succs);   //attempt to move blank up
        attemptMove(temp,1,0,blankI,blankJ,succs);    //attempt to move blank down    
        attemptMove(temp,0,-1,blankI,blankJ,succs);   //attempt to move blank left
        attemptMove(temp,0,1,blankI,blankJ,succs);    //attempt to move blank right

        if (succs.size()==0)
            return null;
        else
            return succs;   
    }
    
    private static int[][] convertStateToArray(State current){
        String value = current.getValue();
        int[][] eightP = new int[3][3];
        for (int i=0; i<eightP.length; i++)
            for (int j=0; j<eightP[i].length; j++)
            {
                eightP[i][j] = Character.digit(value.charAt(i*3+j), 10);
            }
        return eightP;
    }
    
    private static String convertArrayToString(int [][] eightP){
        StringBuffer sb = new StringBuffer();
        for(int[] i:eightP)
            for (int j: i)
                sb.append(String.valueOf(j));
        return sb.toString();
    }
     
    private void attemptMove(int[][] curr, int upDown, int leftRight, int blankI, int blankJ, ArrayList<Node> succs){
        if ((blankJ + upDown >= 0) && (blankJ + upDown <=2) )
            if ((blankI + leftRight >= 0) && (blankI + leftRight <=2) )
            {
                int[][] copy = new int[3][];
                for (int i=0; i<3; i++)
                    copy[i] = Arrays.copyOf(curr[i],3);
                    
                copy[blankI][blankJ] = copy[blankI+leftRight][blankJ+upDown];
                copy[blankI+leftRight][blankJ+upDown]=0;
                State temp = new State(convertArrayToString(copy));
                //System.out.println("Adding: "+ temp.toString());
                succs.add(new EightPuzzleNode(temp, 0,0,0,null));
            }
    }
    
  
}

