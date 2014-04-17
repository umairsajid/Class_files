//Models the Sudoku problem
//Constructor reads board and creates all the variables and constraints

import java.util.*;
public class SudokuProblem {

    private int[][]board;  //NEEDED?
    protected  int size;  // the number of rows
    protected  Variable[][] variables;
    protected  ArrayList<NEConstraint> constraints; 

    public SudokuProblem(int [][] board){
        
        //setup variables
        size = board.length;
        variables = new Variable[size][size];       
        
        //Assign all given values:
        for (int i=0; i<size; i++)
            for(int j=0; j<size; j++){
                int id = i*size + (j+1); //Bookeeping, currently unused
                if (board[i][j]==0)
                    variables[i][j] = new Variable(id,i,j);
                else {//The cell has been assigned a value, which must be used.
                    variables[i][j] = new Variable(id,i,j,new Value(board[i][j]));
                }
            }
        
        constraints = new ArrayList<NEConstraint>();    
        
        //Add all binary not equal constraints
        //The following code adds 972 binary NE constraints to the array list constraints 
        
        //System.out.printf("Starting row constraints\n");
        //Add all row constraints; 324 total
        for (int i=0; i<size; i++){ //for 9 rows 
            for(int j=0; j<size; j++) {//36 constraints per row
                for(int k=j+1; k<size; k++){
                    //System.out.printf("Adding %d,%d --> %d,%d\n",i,j,i,k);
                    constraints.add( new NEConstraint( variables[i][j], variables[i][k]) );
                }
            }
        }
               
        //System.out.printf("Starting column constraints\n");
        //Add all column constraints; 324 total
        for(int j=0; j<size; j++)   {//for 9 columns    
            for (int i=0; i<size; i++) {//36 constraints per column
                for(int k=i+1; k<size; k++){
                    //System.out.printf("Adding %d,%d --> %d,%d\n",i,j,k,j);
                    constraints.add(new NEConstraint(variables[i][j], variables[k][j]));
                }
            }
        }            

         //System.out.printf("Starting subsquare constraints\n");
        //Add all subsquare constraints; Messy...
        for (int rowStart=0; rowStart<size; rowStart+=3) {
            for(int colStart=0; colStart<size; colStart+=3) {//for the 9 subsquares
               //System.out.printf("Starting square %d,%d\n",rowStart,colStart);
               for (int i=rowStart; i<rowStart+3; i++)   {//for each of the cells in the subsquares: total of 36
                    for(int j=colStart; j<colStart+3; j++) {
                        //Selected cell is [i,j]. Find the next cell [m,n]
                        int x,y; 
                        if (j<colStart+2){
                            x = i;
                            y=j+1;  
                        }
                        else {
                            x = i+1;
                            y=colStart; 
                        }
                                
                        for (int m = x; m<rowStart+3; m++)  {
                            int n = y;
                            y = colStart;
                            for(; n<colStart+3; n++) {                               
                                //System.out.printf("Adding %d,%d --> %d,%d\n",i,j,m,n);
                                constraints.add(new NEConstraint(variables[i][j], variables[m][n]) );
                            }  
                         }
                    }
                }
            }
        }        
    }
     
    public void printState(){
        System.out.println(SudokuUtil.formatBoard(getSolution()));
    }

    public int[][] getSolution(){
        //Reformat board from variables
        int[][] output = new int[size][size];
        for (int i=0; i<size; i++)
            for(int j=0; j<size; j++)
                output[i][j] = variables[i][j].getValue().getValue(); //Unfortunate naming scheme...
        return output;
    }
    
    public void setSolutionPath(CBNode node) {           
        while (node !=null){
            Variable variable = node.getVariable();
            variable.setValue(node.getAssigned());           
            node = node.getParent();
        }
    }
   ///////////////////////////////////////////////////////////////////
   //Answer 2(a) and 2(b) here
    public void AC3(){
          //Implement AC3 here      
        LinkedList<Variable> foundAnswer = new LinkedList<Variable>();
        
        for (int j = 0; j < size; j++){  
            for(int i = 0; i < size; i++) {
                Variable var = variables[i][j];
                if (var.getValue() == null){
                    /*empty boxes have domain restricted by those that have answers in column,
                      row, and sub-box */
                    for (int row = 0; row < size; row++){
                        checkConsistency(var, variables[row][var.getJ()]);
                    }
                    for (int col = 0; col < size; col++){
                        checkConsistency(var, variables[var.getI()][col]);
                    }
                    checkSubBox(i, j);
                }
            }
        }
    }

    public void checkConsistency(Variable incomplete, Variable complete){
        if (complete.getValue() !=  null){
            incomplete.makeConsistent(complete);
        }
    }

    public void checkSubBox(int row, int col){
        int modRow = row % 3;//conversion of row from box of 9 to box of 3
        int modCol = col % 3;//conversion of column from box of 9 to box of 3
        int rowFactor = (row - modRow) / 3;//factor to get back to a box of 9
        int colFactor = (col = modCol) / 3;//factor to get back to a box of 9
        int [][] box = new int[][]{{0,0,0},{0,0,0},{0,0,0}};//generic sub-box

        for (int j = 0; j < 3; j++){//marks column already checked
            box[modRow][j] = 1;
        }
        for (int i = 0; i < 3; i++){//markes row already checked
            box[i][modCol] = 1;
        }
        for (int j = 0; j < 3; j++){ //checks to see if other numbers in sub-box are already answered and if so restricts domain
            for(int i = 0; i < 3; i++) {
                if (box[i][j] == 0 && variables[3 * rowFactor + i][3 * colFactor + j].getValue() !=  null){
                    variables[row][col].makeConsistent(variables[3 * rowFactor + i][3 * colFactor + j]);
                }
            }  
        }
    }

    public List<Variable> MRVHeuristic(){
        //Implement the MRV heuristic here by replacing code below
        //which just adds all the variables in order of original creation
        List<Variable> order = new ArrayList<Variable>(); 
        for(int j=0; j<size; j++)   
            for (int i=0; i<size; i++) {
                order.add(variables[i][j]);
            }
        
        Collections.sort(order, new Comparator<Variable>(){//sorts the variables by domain size from smallest to largest
            public int compare(Variable v1, Variable v2){
                if (v1.getDomain().size() < v2.getDomain().size())
                    return -1;
                else if (v1.getDomain().size() == v2.getDomain().size())
                    return 0;
                return 1;
            }
        });

        for (int i = 0; i < 81; i++){//prints order of variables from smallest domain to largest
            System.out.printf("Domain of variable [%d,%d]  = { ",order.get(i).getI(),order.get(i).getJ());
            System.out.print(order.get(i).getDomain());       
            System.out.println( " }" );
        }
        return order;
    }
   /////////////////////////////////////////////////////////////////////// 
    private boolean isGoalState(CBNode node){
        if (node.getDepth()==80) //81 variables successfully assigned
            return true;
        return false;
    }
    
    private List<CBNode> getCurrentAssigned(CBNode node){
        List<CBNode> currentAssigned = new ArrayList<CBNode>();  
        
        while(node !=null){
            currentAssigned.add(node);
            node=node.getParent();
        }
        return currentAssigned;
    }
    
    public boolean chronologicalBacktrack(){
        	Stack<CBNode> openList = new Stack<CBNode>();
        	List<Variable> expandOrder = MRVHeuristic();
        	int depth=0;
        	int numGenerated = 1;
        	int numExpanded = 0;
        	Variable current = expandOrder.get(depth);
        	
        	Collection<CBNode> nodes = current.expand(depth,null);
        	openList.addAll(nodes);       	
	
        	while (!openList.isEmpty()){
        	    CBNode n = openList.pop();
        	    numExpanded++;
        	    
        	    depth = n.getDepth();
                List<CBNode> currentAssigned = getCurrentAssigned(n); 
                
        	    //Check if node n is consistent with all other nodes currently assigned
        	    boolean valid=true;
        	    for (int i=0; i<currentAssigned.size(); i++){
        	        if (!n.isConsistent(currentAssigned.get(i))){
        	           valid=false;
        	           break;  
        	        }
        	    }

        	    if (valid) {//assign and go to next variable
        	       
        	       if (isGoalState(n)) {//First check GOAL
        	           setSolutionPath(n);
        	           System.out.printf("Nodes generated=%d, Nodes expanded=%d\n",numGenerated,numExpanded);
                       return true;
                   }

        	       depth+=1;
        	       current = expandOrder.get(depth);        	
        	       nodes = current.expand(depth,n);      
        	       openList.addAll(nodes);
        	       numGenerated += nodes.size();
        	    }
        	        
        	}
        	//No Solution
        	System.out.printf("Nodes generated=%d, Nodes expanded=%d\n",numGenerated,numExpanded);
        	return false;
    }
}