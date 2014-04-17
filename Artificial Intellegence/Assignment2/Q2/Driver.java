
import java.io.*;

public class Driver
{
    public static void main(String[] args)throws IOException 
    {
        
        // This Sudoku is already solved, obviously it can be "solved" by AC alone.
        //test("solved", true, true); // Time: 0s (bonus 0s)
        
        // This Sudoku can be solved by AC alone
        //test("easy", true, true); // Time: 0s (bonus 0s)
        
        // This Sudoku can not be solved by AC alone, it requires CB.
        //test("hard", true, true); // Time: 0s (bonus 0s)
        
        // Evil level Sudoku from www.websudoku.com, it is difficult for people, easy for AC+CB
        //test("evil", true, true); // Time: 0s (bonus 0s)
        
        // Sudoku from Sudoku enthusiast (http://www.flickr.com/photos/npcomplete/2384354604/), requires CB
        test("starBurstLeo", true, true); // Time: 0s (bonus 0s)


 
    }
        /**
     * @param acSolver Sudoku solver;
     * @param boardName name of the Sudoku board;
     * @param hasSolution if true, then the board has a solution and it will be compared to one obtained by the solver;
     * @param verbose if true, problem board and the solution board will be printed on the screen.
     * 
     * @throws IOException
     */
    private static void test(String boardName, boolean hasSolution, boolean verbose) throws IOException {
        long time = System.currentTimeMillis();
        try {
                System.out.println("Board '" + boardName + "': ");
                int[][] problem = SudokuUtil.readInBoard(boardName + ".sud", 9);
                if (verbose)
                    System.out.println(SudokuUtil.formatBoard(problem));
                    
                //Solve the puzzle    
                SudokuProblem sp = new SudokuProblem(problem);
                sp.AC3();
                if (!sp.chronologicalBacktrack())
                     System.out.println("No Solution...");   
                else{
                    System.out.print("Solution: ");            
                    int[][] solution = sp.getSolution();
                    if (verbose)
                         System.out.println("\n" + SudokuUtil.formatBoard(solution));
                    if (hasSolution)
                        System.out.println(match(solution, SudokuUtil.readInBoard(boardName + "Solution.sud", 9)) ? "CORRECT" : "INCORRECT");
                    else
                        System.out.println("ERROR: board '" + boardName + "' is not a valid Sudoku. Exception should have been thrown.");
                }
        } catch (Exception e) {
            System.out.println("Board '" + boardName + "': crashed " + e);
            e.printStackTrace();
        }
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000 + " seconds\n");
    }

    /**
     * @param board1 Sudoku board;
     * @param board2 Sudoku board.
     * 
     * @return true if given boards are identical, false otherwise.
     */
    private static boolean match(int[][] board1, int[][] board2) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (board1[i][j] != board2[i][j])
                    return false;
        return true;
    }
}