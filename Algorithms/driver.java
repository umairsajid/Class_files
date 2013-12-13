import java.util.Random;
import java.util.Arrays;
/**
 * Write a description of class driver here.
 * 
 * @author (Sterling Kohel) 
 * @version (9/7/2013)
 */
public class driver
{
    public static void makeArray(int size)
    {
        int [] array = new int[size];
        int [] array2 = new int[size];
        Random r = new Random(321);
       
        for (int i=0; i < size; i++)
        {
            int num = r.nextInt();
            array[i] = num;
            array2[i] = num; 
        }

        long startTime = System.nanoTime();
        Arrays.sort(array);
        long estTime = System.nanoTime() - startTime;
        System.out.println("Java sort for " + size + " sized array is " + estTime/ 1000000000.0);  
        
        long startTime2 = System.nanoTime();
        selectionSort(array2);
        long estTime2 = System.nanoTime() - startTime;
        System.out.println("Selection sort for " + size + " sized array is " + estTime2/ 1000000000.0);
        System.out.println("\n");

    }
    
    public static void selectionSort(int[] array)
    {
        for (int loc = 0; loc < array.length; loc++) 
        {
            int min = loc;
            for (int i = loc + 1; i < array.length ; i++)
            {
                if (array[i] < array[min])
                {
                    min = i;
                }
            }
            int temp = array[loc];
            array[loc] = array[min];
            array[min] = temp; 
        }        
               
    }
       
    public static void main(String[] args)
    {
        makeArray(10);
        makeArray(100);
        makeArray(1000);
        makeArray(10000);
        makeArray(100000);
        makeArray(1000000);
    }
}
