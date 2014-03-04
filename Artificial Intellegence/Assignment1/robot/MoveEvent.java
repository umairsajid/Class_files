/**
 * The only event in this simulation: a robot is moving from one
 * location to a neighboring location in the grid
 */
public class MoveEvent
{
    /** the moving robot */
    private Robot r;

    /** the time of the move */
    private int time;

    /**
     * A robot should construct one of these events 
     * when it wants to schedule a move at a later time
     *
     * @param r the robot making the move
     * @param t the time at which the move event will fire
     */
    MoveEvent(Robot r, int time) 
    {
        this.r = r;
        this.time = time;
    }// (int) constructor

    /** return the time of the event */
    public int getTime()
    {
        return time;
    }// getTime method

    /** fire the event */
    public void processEvent()      
    { 
        r.move();
    }// processEvent method

}// MoveEvent class

