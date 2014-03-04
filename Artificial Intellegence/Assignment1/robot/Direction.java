import java.awt.Point;

/**
 * Represents the four compass directions (N,E,S,W) 
 * and the four intermediate ones (NE,SE,SW,NW)
 */
public class Direction  
{
    /** Northern direction */
    public static final int NORTH = 0;

    /** Northeastern direction */
    public static final int NE = 1;

    /** Eastern direction */
    public static final int EAST = 2;

    /** Southeastern direction */
    public static final int SE = 3;

    /** Southern direction */
    public static final int SOUTH = 4;

    /** Southwestern direction */
    public static final int SW = 5;

    /** Western direction */
    public static final int WEST = 6;

    /** Northwestern direction */
    public static final int NW = 7;
 
    /** current direction */
    private int dir;

    /** No-arg constructor: NORTH is the default */
    public Direction () 
    { 
	dir = NORTH; 
    }// no-arg constructor

    /** constructor for a given direction 
     *
     * @param dir one of the predefined directions
     */
    public Direction(int dir) 
    { 
	if (dir<0 || dir >7)
	{
	    System.out.println(dir + " is not a valid direction");
	    System.exit(1);
	}
	this.dir = dir; 
    }// (int) constructor

    /** return the direction */
    public int getDir()
    {
	return dir;
    }// getDir method

    /**
     * Turn a specified number of degrees from a given direction 
     *
     * @param d    starting direction
     * @param deg  number of degrees
     * @return the direction resulting from the turn
     */
    public static Direction turn(Direction d, int deg)
    {
	while(deg < 0)  // make sure that the number of degrees is positive
	    deg+=360;
	return new Direction((d.dir + deg/45) % 8);
    }// turn method

    /**
     * Generate a neighbor Point in the given direction from a given point
     *
     * @param p   the given point
     * @param d   the given direction
     * @return the neighbor point
     */
    public static Point add(Point p, Direction d)
    {	
        //              N  NE  E SE  S  SW   W  NW
	int[] xDir = {  0,  1, 1, 1, 0, -1, -1, -1 };  // horizontal from left
	int[] yDir = { -1, -1, 0, 1, 1,  1,  0, -1 };  // vertical from top

	return new Point(p.x + xDir[d.dir], p.y + yDir[d.dir]);
    }// add method

    /**
     * Check that the given direction is one of the four compass directions. 
     * Exit the program otherwise.
     *
     *  @param dir the direction to be checked
     */
    public static void checkThatMoveIsValid(Direction dir)
    {
	if (!(dir.dir == Direction.NORTH || 
	      dir.dir == Direction.EAST || 
	      dir.dir == Direction.SOUTH || 
	      dir.dir == Direction.WEST)) 
	{
	    System.out.println( "Sorry, the robot cannot move diagonally");
	    System.exit(0);
	}
    }// checkThatMoveIsValid method

}// Direction class
