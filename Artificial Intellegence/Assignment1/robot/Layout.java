
/**
 * The layout describes the status of cells in the grid.
 */
public class Layout  
{
    /** known obstacle */
    public static final char K_OBST  = 'K';

    /** unknown obstacle */
    public static final char U_OBST  = 'U';

    /** known empty cell */
    public static final char K_EMPTY = '|';

    /** unknown empty cell */
    public static final char U_EMPTY = ' ';

    /** empty cell that was previously visited by the robot during this
     *  simulation
     */
    public static final char V_EMPTY = '.';

    /** current position of the robot */
    public static final char ROBOT   = 'R';
}// Layout class
