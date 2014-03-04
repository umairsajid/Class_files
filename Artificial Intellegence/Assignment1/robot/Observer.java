import XtangoAnimation.*;
import java.util.*;
import java.io.*;
import java.awt.Point;
import java.awt.Color;

/**
 *  An observer is responsible for keeping track of the location of the robot,
 *  updating the grid and associated display (if any), and computing the score
 */
public class Observer
{
    /** color of known obstacles */
    Color K_OBST_color = Color.black;

    /** color of unknown obstacles */
    Color U_OBST_color = new Color(150,150,150);

    /** color of known empty cells */
    Color K_EMPTY_color = Color.white;

    /** color of unknown empty cells */
    Color U_EMPTY_color = new Color(220,220,220);

    /** color of visited empty cells */
    Color V_EMPTY_color = Color.yellow;

    /** the grid world in which the simulation takes place */
    private char g[][];

    /** maximum horizontal size of the grid */
    private static final int MAX_X = 100;

    /** maximum vertical size of the grid */
    private static final int MAX_Y = 100;

    /** actual horizontal size of the grid */
    private int cols;

    /** actual vertical size of the grid */
    private int rows;

    /** number of obstacles in the grid */
    private int numObstacles = 0;

    /** number of obstacles found by the robot so far */
    private int numObstaclesFound = 0;

    /** number of moves of the robot so far */
    private int numSteps = 0;

    /** score of the robot */
    private int score = 0;

    /** the simulation to be observed */
    private Simulation sim;

    /** graphics renderer (used if display mode is "g") */
    private XtangoAnimator xa = null;

    /** list of possible start positions for the robot */
    private ArrayList<Point> startPos = new ArrayList<Point>();

    /** current x coordinate of the robot */
    private int robotX = -1;

    /** current y coordinate of the robot */
    private int robotY = -1;

    /** true if the simulation is over */
    private boolean simulationOver;

    /**
     * Construct an observer of the simulation
     *
     * @param filename the name of the file with the layout of the grid
     * @param graphics true if display mode is "g", false otherwise
     * @param sim      the current simulation
     */
    Observer(String filename, boolean graphics, Simulation sim)
    {
	this.sim = sim;
	g = new char [MAX_Y][MAX_X];

	loadLayoutFromFile( filename );	

	dropOffRobot();

	if (graphics)
	    initGraphics();

	simulationOver = false;
    }// constructor	

    /**
     *  Initialize the grid with the layout contained in the given file
     *
     *  @param filename the name of the layout file
     */
    private void loadLayoutFromFile(String filename)
    {
	BufferedReader file = null;
	String buffer = null;

	try {
	    file = new BufferedReader(new FileReader(filename));}
	catch (FileNotFoundException e) {
	    System.out.println("File not found"); 
	    System.exit(0);
	}

	cols = 0;
	rows = 0;

	boolean done = false;
	while (!done) {
	    try {
		buffer = file.readLine();
	    }
	    catch (IOException e) {  }
	    if (buffer != null) { 
		int i = 0;
		while (i < buffer.length()) {
		    g[rows][i] = buffer.charAt(i);
		    i++;
		}		
		cols=i;  // was:     cols=i-1;
		rows++;
	    }
	    else
		done = true;
	}

	try {
	    file.close(); 
	} 
	catch (IOException e) { }
    }// loadLayoutFromFile method

    /**  Drop the robot in the grid at a random empty location surrounded by
     *   empty locations, while counting the number of obstacles in the grid.
     */
    private void dropOffRobot()
    {
	// find all possible dropoff locations
	for (int y = 0; y <= rows; y++)
	    for (int x = 0; x <= cols; x++)
	    {
		if (g[y][x]==Layout.K_OBST)
		{
		    numObstacles++;
		    numObstaclesFound++;
		}
		else if (g[y][x]==Layout.U_OBST)
		    numObstacles++;
		else if (g[y][x]==Layout.ROBOT)
		{
		    robotX = x;
		    robotY = y;
		}
		else if (y>0 && y<rows && x>0 && x<cols &&
			g[y-1][x-1]==Layout.U_EMPTY &&
			g[y-1][x]==Layout.U_EMPTY &&
			g[y-1][x+1]==Layout.U_EMPTY &&
			g[y][x-1]==Layout.U_EMPTY &&
			g[y][x+1]==Layout.U_EMPTY &&
			g[y+1][x-1]==Layout.U_EMPTY &&
			g[y+1][x]==Layout.U_EMPTY &&
			g[y+1][x+1]==Layout.U_EMPTY )

		    startPos.add( new Point(x,y) );
	    }// inner for loop

	if (robotX==-1) // no robot included in the layout
	{
	    if (startPos.size() == 0)
	    {
		System.out.println("No robot in layout and no empty space "
				   + "to dropped one in");
		System.exit(1);
	    }
	    Point p = startPos.get(new Random().nextInt( startPos.size()));
	    robotX = p.x;
	    robotY = p.y;
	    for(int i=-1; i<2; i++)
		for(int j=-1; j<2; j++)
		    g[robotY+i][robotX+j] = '|';
	    g[robotY][robotX] = 'R';

	    sim.setRobot( new Robot(new Point(robotX,robotY),this,sim) );
	}

    }// dropOffRobot method

    /** compute the current score */
    private void computeScore()
    {
	score = numObstaclesFound;
	if (simulationOver)
	    score += (sim.getTotalSteps() - numSteps);

    }// oomputeScore method

    /** return the current score */
    public int getScore()
    {
	return score;
    }// getScore method

    /** return a string description of the current score */
    public String getScoreText()
    {
	return "Found " + numObstaclesFound + " obstacles in " + numSteps
	    + " steps  ####  Score: " + score;
    }// getScoreText method

    /**
     * inquire what is at this particular location
     *
     * @param p a point representing a location in the grid
     * @return one of the character codes in the Layout class
     */
    public char inquire(Point p)   
    {
	return g[p.y][p.x];
    }// inquire method

    /** return true if the two given points are equal */
    private static boolean equals (Point a, Point b)
    {	
	return a.x == b.x && a.y == b.y;
    }// equals method

    /**
     * Used by a robot to notify the observer of its next move
     *
     * @param p the current location of the robot (before the move)
     * @param dir the direction of the move (must be one of Layout.NORTH,
     *        Layout.EAST, Layout.SOUTH, or Layout.WEST)
     */
    void notifyRobotMove(Point p, Direction dir)
    {
	Direction.checkThatMoveIsValid(dir);

	Point newP = Direction.add(p, dir);
	g[p.y][p.x] = Layout.V_EMPTY;
	g[newP.y][newP.x] = Layout.ROBOT;

	numSteps++;
	
	updateGridAfterMove(p,dir,newP);

	if (!simulationOver && allObstaclesFound())
	{
	    simulationOver = true;
	}

	computeScore();

	if (xa!=null)
	{
	    xa.delete("score");
	    xa.bigText("score", 1.2f, -2.0f, false, Color.red, getScoreText() );
	}

    }// notifyRobotMove method

    /**
     *
     *  Called by the observer to update the world after each move
     *
     * @param p1 the origin of the move
     * @param dir the direction of the move
     * @param p2 the destination of the move
     */
    private void updateGridAfterMove(Point p1, Direction dir, Point p2)
    {
	if (xa!=null)
	{
	    xa.color("" + p1.x + "-" + p1.y, V_EMPTY_color);
	    xa.moveAsync("R", 
			 ((float)p2.x) + 0.5f, 
			 ((float)rows) - ((float)p2.y) - 0.5f);
	    xa.delay(10);
	}
	for (int i = 0; i < 8; i++)
	{
	    Point p = Direction.add(p2, Direction.turn(dir, i * 45));
	    
	    switch(inquire(p)) {	
	    case Layout.U_OBST:
		g[p.y][p.x] = Layout.K_OBST;
		if (xa!=null)
		    xa.color("" + p.x + "-" + p.y, K_OBST_color);
		numObstaclesFound++;
		break;
	    case Layout.U_EMPTY:
		g[p.y][p.x] = Layout.K_EMPTY;
		if (xa!=null)
		    xa.color("" + p.x + "-" + p.y, K_EMPTY_color);
		break;
	    }// switch
	}// for loop

	if (xa!=null)
	{
	    xa.delete("score");
	    xa.bigText("score", 1.2f, -2.0f, false, Color.red, getScoreText() );
	}
    }// updateGraphics method


    /** Print the current grid as ASCII text (used when display mode is "t") */
    public void printGrid()
    {
	for (int y = 0; y <= rows; y++)
	{
	    for (int x = 0; x <= cols; x++)
		System.out.print(g[y][x]);
	}
	System.out.println();
    }// printGrid method

    /** return true when all obstacles in the grid have been found 
     *  by the robot 
     */
    public boolean allObstaclesFound()
    {
	return numObstaclesFound == numObstacles;
    }// allObstaclesFound method


    /** draw the initial layout of the grid (only if display mode is "g") */
    private void initGraphics()
    {
	xa = new XtangoAnimator();
	xa.begin();
	xa.coords(-1.5f, -3.0f, 
		  // Xtango requires width=height
		  (float) Math.max(cols,rows) + 1.5f, 
		  (float) Math.max(cols,rows)); 
	xa.bg(Color.white);

	for (int x = 0; x < cols; x++) {
	    for (int y = 0; y < rows; y++) {

		switch (g[y][x]) {
		case Layout.U_EMPTY:
		    xa.rectangle("" + x + "-" + y, (float)x, 
				 (float)rows - (float)y -1.0f, 1.0f, 1.0f, 
				 U_EMPTY_color,  XtangoAnimator.SOLID);
		    break;
		case Layout.V_EMPTY:
		    xa.rectangle("" + x + "-" + y, (float)x, 
				 (float)rows - (float)y - 1.0f, 1.0f, 1.0f, 
				 V_EMPTY_color, XtangoAnimator.SOLID);
		    break;
		case Layout.K_EMPTY:
		    xa.rectangle("" + x + "-" + y, (float)x, 
				 (float)rows - (float)y - 1.0f, 1.0f, 1.0f, 
				 K_EMPTY_color, XtangoAnimator.SOLID);
		    break;
		case Layout.K_OBST:
		    xa.rectangle("" + x + "-" + y, (float)x, 
				 (float)rows - (float)y - 1.0f, 1.0f, 1.0f, 
				 K_OBST_color, XtangoAnimator.SOLID);
		    break;
		case Layout.U_OBST:
		    xa.rectangle("" + x + "-" + y, (float)x, 
				 (float)rows - (float)y - 1.0f, 1.0f, 1.0f, 
				 U_OBST_color, XtangoAnimator.SOLID);
		    break;
		case Layout.ROBOT:
		    xa.rectangle("" + x + "-" + y, (float)x, 
				 (float)rows - (float)y - 1.0f, 1.0f, 1.0f, 
				 V_EMPTY_color, XtangoAnimator.SOLID);

		    break;
		}// switch

	    }// inner for loop
	}// outer for loop
	
	// done outside the loop so that the robot is always visible
	xa.circle(g[robotY][robotX]+"", (float)robotX + 0.5f, 
		  (float)rows - (float)robotY - 0.5f, 0.4f, Color.red, 
		  XtangoAnimator.SOLID);
	
	xa.bigText("score", 1.2f, -2.0f, false, Color.red, getScoreText() );

    }// initGraphics method

}// Observer class


    
