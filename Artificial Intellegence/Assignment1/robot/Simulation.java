import java.awt.*;
import java.util.*;

/**
 * Simulation contains the main driver for this assignment
 */
public class Simulation 
{
    /** the simulated robot */
    private Robot robot;

    /** the queue of simulated events */
    private PriorityQueue <MoveEvent> eventQueue;

    /** the current instant in the simulation */
    private int currentTime;

    /** the observer of this simulation */
    private Observer obs;

    /** the display mode: g (graphics), t (text) or n (none) */
    private String display;

    /** the total duration of the simulation */
    private int totalTime;

    /** the total number of steps (or moves) that the robot is allowed to make */
    private int totalSteps;

    /**
     * Construct a Simulation with its internal event queue
     *
     * @param layout     the name of the layout file
     * @param display    the type of output ("g", "t", or "n")
     * @param totalSteps the maximum number of steps by the robot
     */
    Simulation (String layout, String display, int totalSteps) 
    {
    	this.display = display;
    	this.totalSteps = totalSteps;
    	totalTime = (totalSteps-1)*5; // each move/step takes 
    	                              // 5 (simulated) units of time
    	eventQueue = 
    	    new PriorityQueue<MoveEvent> 
    	    (
    	       100000,
    	       new Comparator<MoveEvent>()
    	       {
    		   public int compare(MoveEvent e1, MoveEvent e2)
    		   {
    		       if(e1.getTime() < e2.getTime())
    			   return -1;
    		       else if(e1.getTime() > e2.getTime())
    			   return 1;
    		       else
    			   return 0;
    		   }// compare method
    	       }// anonymous comparator instance
    	     );
    
    	// create the observer of this simulation
    	obs = new Observer(layout, display.equals("g"), this);
    
    	// the simulation starts at time 0
    	currentTime = 0;

    }// constructor

    /** set the robot for this simulation */
    public void setRobot(Robot r)
    {
    	robot = r;
    }// setRobot method

    /** return the total duration (simulated time) of the simulation */
    public int getTotalTime()
    {
    	return totalTime;
    }// getTotalTime method

    /** return the total number of moves performed by the robot */
    public int getTotalSteps()
    {
    	return totalSteps;
    }// getTotalSteps method

    /** return the current time in the simulation */
    public int getCurrentTime()
    {
    	return currentTime;
    }// getCurrentTime method

    /** return the display mode for this simulation */
    public String getDisplay()
    {
    	return display;
    }// getDisplay method

    /** Add a new event to the simulation's event queue 
     *
     * @param event the event to enqueue
     */
    void scheduleEvent (MoveEvent event)
    { 
    	eventQueue.add (event); 
    }// scheduleEvent method


    /** Run the simulation: fire events until the event queue becomes empty */
    void run()
    {
    	while (! eventQueue.isEmpty()) 
    	{
    	    MoveEvent nextEvent = eventQueue.poll();
    	    currentTime = nextEvent.getTime();
    	    nextEvent.processEvent();
    	}
    }// run method

    /** driver program */
    public static void main(String[] args)
    {
    	String layoutFileName = args[0];
    	String displayMode = args[1];
    	int totalSteps = Integer.parseInt(args[2]);
    	int numRuns = (args.length==3 ? 1 : Integer.parseInt(args[3]));
    	Simulation sim;
    
    	double totalScore = 0.0;
    	for(int run=0; run<numRuns; run++)
    	{
    	    sim = new Simulation(layoutFileName,displayMode,totalSteps);
    	    sim.scheduleEvent(new MoveEvent(sim.robot, 0));
    	    sim.run();	
    	    if (displayMode.equals("t"))
    		sim.obs.printGrid();
    	    totalScore += sim.obs.getScore();
    	    //System.out.println( sim.obs.getScoreText());
    	}
    	System.out.println("Average score = " + (totalScore/numRuns) +"\n");
    }// main method

}// Simulaton class


