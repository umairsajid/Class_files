import java.awt.Point;
//"L1","g","100","10"
/**
 * This is the class in which you will do ALL of your programming 
 * for this assignment. You may NOT modify any of the other classes
 * in the code handout
 */
public class Robot
{
    /** the robot's current position as an (x,y) pair, i.e., a Point */
    private Point pos;

    /** the robot's current direction */
    private Direction dir;

    /** the current simulation */
    private Simulation sim;

    /** the observer of the current simulation */
    private Observer obs;

    // #######################################################
    // define your private instance variables here

    java.util.Random random = new java.util.Random();
    Direction North = new Direction(Direction.NORTH);
    Direction South = new Direction(Direction.SOUTH);
    Direction East = new Direction(Direction.EAST);
    Direction West = new Direction(Direction.WEST);
    Direction FR;
    Direction FL;
    Direction FC;
    Direction right;
    Direction left;
    Direction last;
    // #######################################################
    
    /**
     *  Construct a robot at a given position (by default, the robot is
     *  facing NORTH)
     *
     * @param pos the position where the robot is dropped off
     * @param the observer of this simulation
     * @param the current simulation
     */
    Robot(Point pos, Observer obs, Simulation sim)
    {
        this.pos = pos;
        dir = new Direction (Direction.NORTH);
        this.obs = obs;
        this.sim = sim;
    }// cosntructor

    /** Perform a move (called when a MoveEvent fires) */
    public void move()
    {
        // print the grid before the move (if in text mode)
        if (sim.getDisplay().equals("t"))
            obs.printGrid();
    
        pickMove();    // pick a direction 
        executeMove(); // move in the chosen direction
    }// move method

    /** move in the current direction */
    private void executeMove()
    {   
        // if all obstacles have been found: do nothing; otherwise ...
        if (!obs.allObstaclesFound())
        {
            // if the chosen direction leads to a free location
            if (isOpen(obs. inquire(Direction.add(pos, dir))))
            {
                obs.notifyRobotMove(pos, dir); // norify the observer
                pos = Direction.add(pos, dir); // and perform the move
            }
    
            // if time has not run out, schedule the next move
            if (sim.getCurrentTime() < sim.getTotalTime())
                sim.scheduleEvent(new MoveEvent(this,sim.getCurrentTime()+5));
        }// not done yet
    }// executeMove method


    /** 
     * returns true if the given status corresponds to an empty cell (which
     * may be known, unknown, or visited)
     *
     * @param status a Layout code
     * @return true if the status is that of an empty cell
     */
    private boolean isOpen(char status)
    {
        return 
            ( status==Layout.K_EMPTY || 
              status==Layout.V_EMPTY || 
              status==Layout.U_EMPTY    );
    }// isOpen method

    // #######################################################
    // modify this method

    /** pick a direction for the robot to follow */

    private void pickMove() 
    {    
        findFront();    
        if (isFrontClear() && last == null){            
            dir = North;
        }
        else if (isFrontClear() && last != null){
            dir = straight();
        }
        else if (isAPath(obs.inquire(Direction.add(pos, FC))) && isUnvisited(obs.inquire(Direction.add(pos, right)))){
            dir = turnRight();
        }
        else if (isAPath(obs.inquire(Direction.add(pos, FC))) && isUnvisited(obs.inquire(Direction.add(pos, left)))){
            dir = turnLeft();
        }
        else if (isOpen(obs.inquire(Direction.add(pos, FR))) && isOpen(obs.inquire(Direction.add(pos, FC))) || 
                 isOpen(obs.inquire(Direction.add(pos, FL))) && isOpen(obs.inquire(Direction.add(pos, FC)))){
            dir = straight();
        }
        else if (isWall() && isUnvisited(obs.inquire(Direction.add(pos, right))) ||
            !isUnvisited(obs.inquire(Direction.add(pos, FC))) && isUnvisited(obs.inquire(Direction.add(pos, right)))){
            dir = turnRight();
        }
        else if (isWall() && isUnvisited(obs.inquire(Direction.add(pos, left))) ||
            !isUnvisited(obs.inquire(Direction.add(pos, FC))) && isUnvisited(obs.inquire(Direction.add(pos, left)))){
            dir = turnLeft();
        }
        else{
            dir = randomDirection();
        }
        last = dir;
    }// pickMove method

    // #######################################################
    // define your private helper methods below this point
    
    private void findFront(){
        if (dir == North){
            FL = new Direction(Direction.NW);
            FC = new Direction(Direction.NORTH);
            FR = new Direction(Direction.NE);
            right = new Direction(Direction.EAST);
            left = new Direction(Direction.WEST);
        }
        else if (dir == West){
            FL = new Direction(Direction.SW);
            FC = new Direction(Direction.WEST);
            FR = new Direction(Direction.NW);
            right = new Direction(Direction.NORTH);
            left = new Direction(Direction.SOUTH);
        }
        else if (dir == South){
            FL = new Direction(Direction.SE);
            FC = new Direction(Direction.SOUTH);
            FR = new Direction(Direction.SW);
            right = new Direction(Direction.WEST);
            left = new Direction(Direction.EAST);
        }
        else{
            FL = new Direction(Direction.NE);
            FC = new Direction(Direction.EAST);
            FR = new Direction(Direction.SE);
            right = new Direction(Direction.SOUTH);
            left = new Direction(Direction.NORTH);
         }
    }

    private Direction turnRight(){
        if (dir == North)
            return East;
        else if (dir == East)
            return South;
        else if (dir == South)
            return West;
        else 
            return North;
    }

    private Direction turnLeft(){
         if (dir == North)
            return West;
        else if (dir == East)
            return North;
        else if (dir == South)
            return East;
        else 
            return South;
    }

    private Direction straight(){
         if (dir == North)
            return North;
        else if (dir == East)
            return East;
        else if (dir == South)
            return South;
        else 
            return West;
    }

    private boolean isFrontClear(){
        findFront();
        return (isUnvisited(obs.inquire(Direction.add(pos, FL))) && 
                isUnvisited(obs.inquire(Direction.add(pos, FR))) && 
                isUnvisited(obs.inquire(Direction.add(pos, FC))));
    }

    private boolean isWall(){
        findFront();
        return (!isOpen(obs.inquire(Direction.add(pos, FL))) && 
                !isOpen(obs.inquire(Direction.add(pos, FR))) && 
                !isOpen(obs.inquire(Direction.add(pos, FC))));
    }

    private boolean isAPath(char status)
    {
        return status==Layout.V_EMPTY;
    }

    private boolean isUnvisited(char status)
    {
        return (
                status == Layout.K_EMPTY || 
                status == Layout.U_EMPTY);
    }

    private Direction randomDirection(){
        int r = random.nextInt(4);
        if (r == 0)
            return North;
        else if (r == 1)
            return East;
        else if (r == 2)
            return West;
        else 
            return South;
    }
    // #######################################################
    // define your inner classes below this point

}// Robot class