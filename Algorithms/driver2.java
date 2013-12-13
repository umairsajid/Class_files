import java.util.*;
import java.io.*;
/**
 * Tests the speed of a 2^n algorithm vs a n^6 algorithm vs a linear algorithm.
 * 
 * @author (Sterling Kohel) 
 * @version (Sept 15, 2013)
 */
public class driver2
{
    int[] array = new int[52]; 
    public void readfile() throws IOException
    {
        int index = 0;
        Scanner scan = new Scanner(new File("test.txt"));
        
        scan.useDelimiter("[\\s;,]+");
                  
        while(scan.hasNext())
        {
            array[index] = scan.nextInt();
            index++;
        }
        scan.close();
    }
    
    public boolean findFactors(int num)
    {
        for (int arrayIndex = 0; arrayIndex < array.length-1;arrayIndex++) 
        {
            for (int i = 1; i < num; i++)
            {
                for (int j = 1; j < num; j++)
                {
                    for (int k = 1; k < num; k++)
                    {
                        for (int m = 1; m < num; m++)
                        {
                            for (int n = 1; n < num; n++)
                            {
                                for (int p = 1; p < num; p++)
                                {
                                     if (num == i*j*k*m*n*p)
                                        return true;                                                     
                                }                        
                            }                                
                        }
                     }
                }
            } 
        }    
        return false;
    }
    
    public void sum(int[] array)
    {
        long sum = 0;
        for (int i = 0; i < array.length - 1; i++)
        {
            sum += array[i];
        }
    }
    
    public int countFactors(int n) 
    { 
        int count = 0; 
        
        for (int i = 1; i <= n; i++) 
        { 
            if (n % i == 0) 
            {
                if (i != 1 && i != n)
                    countFactors(i);
            }
        } 
        return count; 
    } 
    
    public static void main(String[] args)
    {
        driver2 d =  new driver2();
        try
        {
            d.readfile();
        }
        catch(IOException e)
        {
            System.out.println("File could not be opened!");
        }
       
        
        long startTime= System.nanoTime();
        for(int i = 0; i < d.array.length -1; i++)
        {
             d.countFactors(d.array[i]);
        }
        long estTime = System.nanoTime() - startTime;
        System.out.printf("Time taken for exponential %.2f\n " , (estTime/ 1000000000.0)/60);
        
        long startTime2  = System.nanoTime();
        for(int i = 0; i < d.array.length -1; i++)
        {
            d.findFactors(d.array[i]);
        }
        long estTime2 = System.nanoTime() - startTime2;
        System.out.printf("Time taken for n^6, %.2f\n " , (estTime2/ 1000000000.0)/60);
        
        long startTime3 = System.nanoTime();
        d.sum(d.array);
        long estTime3 = System.nanoTime() - startTime3;
        System.out.printf("Time taken for linear, %.10f\n " , (estTime3/ 1000000000.0)/60);
    }
}







