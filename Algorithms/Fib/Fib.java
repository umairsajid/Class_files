import java.util.Arrays;
import java.io.*;
public class Fib
{
    int[][] sequence = new int[2][28];
    int nextSpotPointer = 3, parentPointer = 1, height;
    Node[] order;
    RootNode root;
    LeftChild lc;
    RightChild rc;
    public Fib(int num)
    {
        Fill();
        height = num - 3;
        order = new Node[sequence[1][num]-1];
        Arrays.fill(order, new RootNode(0,0,0));
        root = new RootNode(sequence[1][num-1]-1, height,sequence[1][num-3]);
        order[0] = root;
        height--;
        lc = new LeftChild(order[0].getValue() - order[0].getSpread(), height, 0, sequence[1][num-4]);
        rc = new RightChild(order[0].getValue() + order[0].getSpread(), height, 0, sequence[1][num-5]);
        order[1] = lc;
        order[2] = rc;
    }
    
    public void Fill()
    {
        sequence[0][0] = 0;
        sequence[1][0] = 0;
        sequence[0][1] = 1;
        sequence[1][1] = 1;

        for (int i = 2; i < 28; i++)
        {
            sequence[0][i] = i;
            sequence[1][i] = sequence[1][i-1] + sequence[1][i-2];           
        }
    }

    public void Print()
    {
       int i = 0;
       try
       {
           PrintWriter output = new PrintWriter("q6.txt");
           
           for(Node node : order)
            {
                output.println(node.getValue() );
                i++;
            }
           output.close();
        }
        catch(Exception e){}
        i = 0;
    }
    
    public int findSpread(int n)
    { 
        int i=0;
        while (true)
        {
            if (sequence[1][i] == n)
                break;
                i++;
        }
       return (sequence[0][i]-1);
    }
    
    public int findSpread2(int n)
    { 
        int i=0;
        while (true)
        {
            if (sequence[1][i] == n)
                break;
                i++;
        }
        return (sequence[0][i]-2);
    }
    public void makeNodes()
    {
        while (height != 0)
        {
            if (order[parentPointer].getHeight() < order[parentPointer-1].getHeight())
              height--;
            if (order[parentPointer].getSpread() > 1)
            {
                lc = new LeftChild(order[parentPointer].getValue() - order[parentPointer].getSpread(), 
                              height, parentPointer, sequence[1][findSpread(order[parentPointer].getSpread())]);
                order[nextSpotPointer] = lc;
                nextSpotPointer++;
            }
            else if (order[parentPointer].getSpread() == 1)
            {
                if (order[order[parentPointer].getPP()].getSpread() > 2)
                {
                    if (order[parentPointer] instanceof RightChild)
                    {
                        lc = new LeftChild(order[parentPointer].getValue() - 1, height, parentPointer, 1);
                        order[nextSpotPointer] = lc;
                        nextSpotPointer++;
                    }
                    else 
                    {
                        lc = new LeftChild(order[parentPointer].getValue() - 1, height, parentPointer, 0);
                        order[nextSpotPointer] = lc;
                        nextSpotPointer++;
                    }
                }
              
                else if (order[order[parentPointer].getPP()].getSpread() > 1)
                {
                    if (order[parentPointer] instanceof LeftChild)
                    {
                        lc = new LeftChild(order[parentPointer].getValue() - 1, height, parentPointer, 1);
                        order[nextSpotPointer] = lc;
                        nextSpotPointer++;
                    }
                    else 
                    {
                        lc = new LeftChild(order[parentPointer].getValue() - 1, height, parentPointer, 0);
                        order[nextSpotPointer] = lc;
                        nextSpotPointer++;
                    }
                }
                              
                else if (order[order[parentPointer].getPP()].getSpread() == 1)
                {
                    lc = new LeftChild(order[parentPointer].getValue() - 1, height, parentPointer, 0);
                    order[nextSpotPointer] = lc;
                    nextSpotPointer++;
                }
            }
            if (order[parentPointer].getSpread() > 1)
            {
                rc = new RightChild(order[parentPointer].getValue() + order[parentPointer].getSpread(), 
                              height, parentPointer, sequence[1][findSpread2(order[parentPointer].getSpread())]);
                order[nextSpotPointer] = rc;
                nextSpotPointer++;
            }
            else if (order[parentPointer].getSpread() == 1)
            {
                if (order[order[parentPointer].getPP()].getSpread() > 2 )
                {
                    if (order[parentPointer] instanceof RightChild)
                    {
                        rc = new RightChild(order[parentPointer].getValue() + 1, height, parentPointer, 0);
                        order[nextSpotPointer] = rc;
                        nextSpotPointer++;
                    }
                    else
                    {
                        rc = new RightChild(order[parentPointer].getValue() + 1, height, parentPointer, 1);
                        order[nextSpotPointer] = rc;
                        nextSpotPointer++;
                    }
                }
                 else if (order[parentPointer].getSpread() == 1)
                 {
                     if (order[order[parentPointer].getPP()].getSpread() > 2) 
                     {
                         rc = new RightChild(order[parentPointer].getValue() + 1, height, parentPointer, 0);
                         order[nextSpotPointer] = rc;
                         nextSpotPointer++;
                     }

                     else if (order[order[parentPointer].getPP()].getSpread() == 2 && order[parentPointer] instanceof LeftChild)
                     {
                         rc = new RightChild(order[parentPointer].getValue() + 1, height, parentPointer, 0);
                         order[nextSpotPointer] = rc;
                         nextSpotPointer++;
                     }    
                     
                     
 
                }
            }
            parentPointer++;
        }
    }
    public static void main(String args[])
    {
        int n = 27;
        Fib f = new Fib(n);
        f.makeNodes();
        f.Print();
        
    }
}