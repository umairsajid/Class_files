import java.util.*;
import java.io.*;

public class tester
{
    int[] myArray, myArray2;
    int[][] array; 
    String[] charArray;
    
    public int count(String filename) throws IOException
    {
        int index = 0;
        Scanner scan = new Scanner(new File(filename));
        
        scan.useDelimiter("[\n]+");
                  
        while(scan.hasNext())
        {
            scan.nextLine();
            index++;
        }
        scan.close();     
        return index;    
    }

    public void readfile(String filename, int size) throws IOException
    {
        int index = 0;
        Scanner scan = new Scanner(new File(filename));
        myArray = new int[size];
        
        scan.useDelimiter("[\\s;,]+");

        while(scan.hasNext())
        {
            myArray[index] = scan.nextInt();
            index++;
        }
        scan.close();                 
    }

    public int peak(int[] arr, int low, int high)
    {
        int mid = (low + high) / 2;
        if(arr[mid] >= arr[mid - 1] && arr[mid] >= arr[mid + 1])
            return mid;
        else if (arr[mid + 1] > arr[mid])
            return peak(arr, mid + 1, high);
        return peak(arr, low, mid - 1);
    }

    public void readfile2(String filename, int size) throws IOException
    {
        int index = 0;
        array = new int[size][2];
        Scanner scan = new Scanner(new File(filename));
        
        scan.useDelimiter("[\\s;,]+");
                  
        while(scan.hasNext())
        {
            array[index][0] = scan.nextInt();
            array[index][1] = scan.nextInt();
            index++;
        }
        scan.close();
    }

    public int[][] split(int[][] arr)
    {
        if (arr.length == 1)
            return arr;
         
        int[][] leftside  = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[][] rightside = Arrays.copyOfRange(arr, arr.length / 2 , arr.length);
        leftside = split(leftside);
        rightside = split(rightside);
        
        return merge(leftside, rightside);          
    }

    public int[][] merge(int[][] array1, int [][] array2)
    {
        int[][] temp = new int[array1.length + array2.length][2];
        int array1Index = 0;
        int array2Index = 0;
        int tempIndex = 0;

        while(array1Index < array1.length && array2Index < array2.length)
        {
            if (array1[array1Index][1] < array2[array2Index][1])
            {
                temp[tempIndex][0] = array1[array1Index][0];
                temp[tempIndex++][1] = array1[array1Index++][1];
            }
            else
            {
                temp[tempIndex][0] = array2[array2Index][0];
                temp[tempIndex++][1] = array2[array2Index++][1];
            } 
        }
        while (array1Index < array1.length)
        {
            temp[tempIndex][0] = array1[array1Index][0];
            temp[tempIndex++][1] = array1[array1Index++][1];
        } 
        while (array2Index < array2.length)
        {
            temp[tempIndex][0] = array2[array2Index][0];
            temp[tempIndex++][1] = array2[array2Index++][1];
        }    
        return temp;
    }

    public int place(int[] listOne, int[] listTwo, int index)
    {
        int mid, low, high;
        if (index == 1)
            return listOne[0];
        else if (index == listOne.length * 2)
            return listOne[listOne.length-1];
        low = 0;
        high = listOne.length - 1;    
        
        while (true)
        {   
            mid = (low + high) / 2; 
            if ((mid*2) == index)
                break;
            if (mid*2 > index)
                high = mid - 1;
            else 
                low = mid +1;
        }
        return listOne[mid];
    }     

    public void readCharFile(String filename, int size) throws IOException
    {
        int index = 0;
        Scanner scan = new Scanner(new File(filename));
        charArray = new String[size];
        
        scan.useDelimiter("[\\s;,]+");

        while(scan.hasNext())
        {
            charArray[index] = scan.nextLine();
            index++;
        }
        scan.close();  
                     
    }

    public static void selectionSort(String[] array, int loc)
    {
        int min = loc, i = loc + 1;
        Boolean swap = false;
        while (true)
        {
            if (i == array.length)
                break;
            if (array[min].equals(array[i]))
            {
                min = i;
                swap = true;
                break;                
            }
            i++;
        }
        if(swap == true)
        {    
            String temp = array[loc+1];
            array[loc+1] = array[min];
            array[min] = temp; 
        }
        swap = false;
        if (loc < array.length-2)   
            selectionSort(array, loc+1);     
               
    }
    public static void main(String[] args)
    {
        tester t = new tester();
        
        try
        {
            int size = t.count("Q2.txt");
            // t.readfile("Q2.txt", size);            
            // System.out.println(t.peak(t.myArray, 0, size));
            size = t.count("Q3.txt");
            System.out.println("the size of q3 is " + size);
            t.readfile2("Q3.txt", size); 
            int[][] test= t.split(t.array);
            for(int i = 0; i < t.array.length;i++)
            {
                System.out.print(test[i][0] + " " + test[i][1] + "\n");            
            }  
            int size2 = t.count("Q4listTwo.txt");
            t.readfile("Q4listTwo.txt", size2);
            t.myArray2 = t.myArray; 
            size = t.count("Q4listOne.txt");
            t.readfile("Q4listOne.txt", size); 
            Scanner input = new Scanner(System.in);
            System.out.print("Enter a place to check: ");
            int num = input.nextInt();
            System.out.println(t.place(t.myArray,t.myArray2, num));
            
            t.readCharFile("Q5.txt", t.count("Q5.txt"));
            t.selectionSort(t.charArray, 0);
            String color = t.charArray[0];
            double count = 1.0;
            for(int i = 1; i< t.charArray.length;i++)
            {
                if(t.charArray[i].equals(color))
                    count++;
                else 
                {
                    if (count/t.charArray.length > .5)
                        System.out.println("System has a preference for " + color + ". It has a percentage of " +(count/t.charArray.length));
                    else
                    {
                       System.out.println(color + " has a percentage of " +(count/t.charArray.length));
                       color = t.charArray[i];
                       count = 1.0;
                    }
               }
            }
                
        }
        catch(IOException e)
        {
            System.out.println("File could not be opened!");
        }
    }
}