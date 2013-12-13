import java.util.*;
import java.io.*;

public class driver
{
	//**************** begin common files ***************************
    //**************** used to count size of files ******************
    public static int count(String filename) throws IOException
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
    //************ reads file into single array *********
    public static int[] readfile(String filename, int size) throws IOException
    {
        int index = 0;
        int[] myArray;
        Scanner scan = new Scanner(new File(filename));

        myArray = new int[size];
        scan.useDelimiter("[\\s;,]+");

        while(scan.hasNext())
        {
            myArray[index] = scan.nextInt();
            index++;
        }
        scan.close();
        return myArray;                 
    }
    //**************** end common files ***************************

    //**************** begin of q2 files ****************************
    //**************** finds highest value in array******************
    public static int peak(int[] arr, int low, int high)
    {
        int mid = (low + high) / 2;
        if(arr[mid] >= arr[mid - 1] && arr[mid] >= arr[mid + 1])
            return mid;
        else if (arr[mid + 1] > arr[mid])
            return peak(arr, mid + 1, high);
        return peak(arr, low, mid - 1);
    }
    // ***************** admin code to run q2****************
    public static void q2()
    {
        System.out.println("Starting problem 2");
    	try
    	{
            int[] myArray;
    		int size = count("Q2.txt");
            myArray = new int[size];
    		myArray = readfile("Q2.txt", size);            
            System.out.println(peak(myArray, 0, size));
    	}

    	catch(IOException e)
        {
            System.out.println("Q2 could not be opened!");
        }       
    }
    //*************************end of q2 files******************

    //*************************begin of q3 files****************
    //*************************reads file into 2d array ********
    public static int[][] readfile2(String filename, int size) throws IOException
    {
        int[][] array;
        int index = 0;
        array = new int[size][3];
        Scanner scan = new Scanner(new File(filename));
        
        scan.useDelimiter("[\\s;,]+");
                  
        while(scan.hasNext())
        {
            array[index][0] = scan.nextInt();
            array[index][1] = scan.nextInt();
            array[index][2] = index;
            index++;
        }
        scan.close();
        return array;
    }

    //************* splits array into arrays of singles*****************
    public static int[][] split(int[][] arr)
    {
        if (arr.length == 1)
            return arr;
         
        int[][] leftside  = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[][] rightside = Arrays.copyOfRange(arr, arr.length / 2 , arr.length);
        leftside = split(leftside);
        rightside = split(rightside);
        
        return merge(leftside, rightside);          
    }
    //*******************combines single arrays **************************
    public static int[][] merge(int[][] array1, int [][] array2)
    {
        int[][] temp = new int[array1.length + array2.length][3];
        int array1Index = 0;
        int array2Index = 0;
        int tempIndex = 0;

        while(array1Index < array1.length && array2Index < array2.length)
        {
            if (array1[array1Index][1] <= array2[array2Index][1])
            {
                temp[tempIndex][0] = array1[array1Index][0];
                temp[tempIndex][1] = array1[array1Index][1];
                temp[tempIndex++][2] = array1[array1Index++][2];
            }
            else
            {
                temp[tempIndex][0] = array2[array2Index][0];
                temp[tempIndex][1] = array2[array2Index][1];
                temp[tempIndex++][2] = array2[array2Index++][2];
            } 
        }
        while (array1Index < array1.length)
        {
            temp[tempIndex][0] = array1[array1Index][0];
            temp[tempIndex][1] = array1[array1Index][1];
            temp[tempIndex++][2] = array1[array1Index++][2];
        } 
        while (array2Index < array2.length)
        {
            temp[tempIndex][0] = array2[array2Index][0];
            temp[tempIndex][1] = array2[array2Index][1];
            temp[tempIndex++][2] = array2[array2Index++][2];
        }    
        return temp;
    }

    //****************** splits so array can be sorted by indexition******************
    public static int[][] split2(int[][] arr)
    {
        if (arr.length == 1)
            return arr;
         
        int[][] leftside  = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[][] rightside = Arrays.copyOfRange(arr, arr.length / 2 , arr.length);
        leftside = split2(leftside);
        rightside = split2(rightside);
        
        return mergepic(leftside, rightside);          
    }

    //***********************sorts by indexition************************************
    public static int[][] mergepic(int[][] array1, int [][] array2)
    {
        int[][] temp = new int[array1.length + array2.length][3];
        int array1Index = 0;
        int array2Index = 0;
        int tempIndex = 0;

        while(array1Index < array1.length && array2Index < array2.length)
        {
            
            if (array1[array1Index][2] <= array2[array2Index][2] && array1[array1Index][2] !=-1) 
            {
                temp[tempIndex][0] = array1[array1Index][0];
                temp[tempIndex][1] = array1[array1Index][1];
                temp[tempIndex++][2] = array1[array1Index++][2];
            }
            else if (array1[array1Index][2] > array2[array2Index][2] && array2[array2Index][2] !=-1)
            {
                temp[tempIndex][0] = array2[array2Index][0];
                temp[tempIndex][1] = array2[array2Index][1];
                temp[tempIndex++][2] = array2[array2Index++][2];
            }         
            else if (array1[array1Index][2] == -1)     
                array1Index++;
            else if (array2[array2Index][2] == -1)     
                array2Index++;
        }
        while (array1Index < array1.length)
        {
            temp[tempIndex][0] = array1[array1Index][0];
            temp[tempIndex][1] = array1[array1Index][1];
            temp[tempIndex++][2] = array1[array1Index++][2];
        } 
        while (array2Index < array2.length)
        {
            temp[tempIndex][0] = array2[array2Index][0];
            temp[tempIndex][1] = array2[array2Index][1];
            temp[tempIndex++][2] = array2[array2Index++][2];
        }  
        int emptyslot = 0;
        for (int i = 1; i < temp.length; i++) 
        {
            if (temp[i][2] == 0)
                emptyslot++;
        } 
         
        // new array elimanates empty spaces left by duplicates
        int [][] temp2 = Arrays.copyOfRange(temp, 0, temp.length - emptyslot);    
                   
        
        return temp2;
    }

    //***************************admin code to run q3********
    public static void q3()
    {
        System.out.println("Starting problem 3");
        try
        {
            int[][] array, array2;
            int i = 1;

            System.out.println("Counting number of lines");
            int size = count("Q3.txt");
            array = new int[size][];
            System.out.println("Reading file into array");
            array = readfile2("Q3.txt", size); 
            System.out.println("Mergesorting by bib number");
            int[][] test = split(array);
            int bibnum = array[0][1];

            System.out.println("Marking duplicates");
            while (i != test.length)
            {
                if (test[i][1] == bibnum)
                    test[i][2] = -1;
                else 
                    bibnum = test[i][1];
                i++;
            }   
           
            System.out.println("");
            array2 = new int[size][];
            System.out.println("Resorting by appearance and removing duplicates");
            array2 = split2(test);

            for(int j = 0; j < array2.length; j++)
            {

                System.out.println(array2[j][2]);
            }            
        }

        catch(IOException e)
        {
            System.out.println("Q3 could not be opened!");
        }
    }
    //************************* end of q3 files ******************

    //************************* begin of q4 files ****************
    //************************* finds place of the runner ********
    public static int place(int[] listOne, int[] listTwo, int index)
    {
        if (listOne[index / 2] < listTwo[index / 2])
            place(Arrays.copyOfRange(listOne, index / 2, listOne.length - 1),
                  Arrays.copyOfRange(listTwo, 0, index / 2), index);
        else if (listOne[index / 2] > listTwo[index / 2])
            place(Arrays.copyOfRange(listOne, 0, index / 2),
                  Arrays.copyOfRange(listTwo, index / 2, listTwo.length - 1), index);

        return listOne[index / 2];
    }    


    //***************************admin code to run q4********
    public static void q4()
    {
        System.out.println("Starting problem 4");
        try
        {
            int[] listOne, listTwo;
            int size = count("Q4ListOne.txt");
            listOne = new int[size];
            listOne = readfile("Q4ListOne.txt", size); 
            listTwo = new int[size];
            listTwo = readfile("Q4ListTwo.txt", size);
            Scanner input = new Scanner(System.in);
            System.out.print("Enter a place to check: ");
            int num = input.nextInt();
            System.out.println(place(listOne,listTwo, num));
        }

        catch(IOException e)
        {
            System.out.println("A file for Q4 could not be opened!");
        }
    }
    //************************* end of q4 files ******************

    //************************* begin of q5 files ****************

    // completed using Boyer-Moore algorithm 
    // http://www.cs.utexas.edu/~moore/best-ideas/mjrty/
    public static void q5()
    {
        System.out.println("Starting problem 5");
        try
        {
            int i = 1, midpointsum, sum = 1;
            String[] charArray;
            String midpointcolor, color;

            int size = count("Q5.txt");
            charArray = new String[size];
            charArray = readCharFile("Q5.txt", size);

            color = charArray[0];

            while (true)
            {
                if (i == charArray.length)
                    break;
                if(charArray[i].equals(color))
                    sum++;
                else if (charArray[i] != color && sum == 0)
                    color = charArray[i];
                else
                    sum--;
                i++;
            }

            i = 0;
            midpointcolor = color;
            midpointsum = sum;

            while (true)
            {
                if (i == charArray.length)
                    break;
                if(charArray[i].equals(color))
                    sum++;
                else if (charArray[i] == color && sum == 0)
                    color = charArray[i];
                else
                    sum--;
                i++;
            }

            if (color.equals(midpointcolor) && sum > midpointsum)
                System.out.println("There is a bias for " + color);
            else
                System.out.println("There is no bias for any colors");
        }

          
        catch(IOException e)
        {
            System.out.println("A file for Q5 could not be opened!");
        }
    }
    //*******************end q5**********************

    //****************begin q5a***********************

    //********************* reads file into single array *********
    public static String[] readCharFile(String filename, int size) throws IOException
    {
        String [] charArray;
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
        return charArray;  
                     
    }

    //***************sorts strings by equality**************
    public static String[] selectionSort(String[] array, int loc)
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
        return array;               
    }

    //***************************admin code to run q5********
    public static void q5a()
    {
        try
        {
            int i = 1;
            String[] charArray;

            int size = count("Q5a.txt");
            charArray = new String[size];
            charArray = readCharFile("Q5a.txt", size);
            selectionSort(charArray, 0);
            String color = charArray[0];
            double count = 1.0;
            while(true)
            {
                if (i == size)
                    break;
                if(charArray[i].equals(color))
                    count++;
                else 
                {
                    System.out.println("color " + color + " count " + count + " size " + (size-1));
                    if (count/charArray.length > .5)
                        System.out.println("System has a preference for " + color  
                                           + ". It has a percentage of " + (count/(size-1)));
                    else
                    {
                       System.out.println(color + " has a percentage of " + (count/(size-1)));
                       color = charArray[i];
                       count = 1.0;
                    }
                    color = charArray[i];
                    count = 0;
               }
               i++;
            }
        }    
        catch(IOException e)
        {
            System.out.println("A file for Q5 could not be opened!");
        }
    }

    
    public static void main(String[] args)
    {
    	q2();
        q3();
        q4();
        q5();
        //q5a();//finds bias using recursive selection sort
    }
}