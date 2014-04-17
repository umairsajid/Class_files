/**
 * The main class that matches a problem to solve and an algorithm to run on it.
 */

import java.util.Stack;
public class Solver {    
    public static void main(String[] args) {   
        //Example arguments:
        //"BFS_DD","Romania","Arad" 
        //"BFS_DD","MC"
        //"AStar","EightPuzzle","120345678","1" 
        //or
        //"AStar","EightPuzzle","120345678","2" 
        //"BFS_DD","EightPuzzle","120345678","2"
        
        String algoName = args[0];
        SearchAlgorithm algo = null;
        
        if (algoName.equalsIgnoreCase("BFS_DD"))
            algo = new BFS_DD();
        //else if (algoName.equalsIgnoreCase("AStar"))
        //    algo = new AStar();          
        
        String problemName = args[1];
        Problem problem = null;
        
        if (problemName.equalsIgnoreCase("MC"))
            problem = new MCProblem();         
        else if (problemName.equalsIgnoreCase("Romania"))
            problem = new RomaniaProblem(args[2]);       
        else if (problemName.equalsIgnoreCase("EightPuzzle"))
            problem = new EightPuzzle(args[2], Integer.parseInt(args[3]));

                                  
        long startTime = System.currentTimeMillis();
        Stack<Node> path = algo.search(problem);
        long runTime = System.currentTimeMillis() - startTime;
        if (path==null)
            System.out.println("No solution found (" +
                algo.getNumGenerated() + " nodes generated)");
        else {
            int pathLength = path.size()-1;
            printPath(path);  // Will destroy the path in this process
            System.out.println("Run time: " + (runTime/1000) + " seconds");
            System.out.println("Solution path contains " + 
                       pathLength + " steps:");
            System.out.println( "[" + algo.getNumGenerated() + 
                    " nodes generated]");
        }
    }// main method
    
    //Note: path is destroyed in this call!
    private static void printPath(Stack<Node> path){
        int step = 1;
        while (!path.empty()) {
            Node n = path.pop();
            System.out.println(n.getState().toString());
            if (!path.empty())
                System.out.println("Step " + step++ + ":");
            else 
                System.out.println("Optimal Cost: " + n.getG());
        }
    }// printPath method

}
