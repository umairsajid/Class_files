
/**
 * Abstract class node - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Node
{
    int value;
    int height;
    int parentPointer;
    int spread;
    public Node(int v, int h, int pp, int n)
    {
        value = v;
        height = h;
        parentPointer = pp;
        spread = n;
    }
    
    public int getValue()
    {
        return value;
    }
    
    public int getSpread()
    {
        return spread;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public int getPP()
    {
        return parentPointer;
    }
}
