import java.util.*;
import java.io.*;

class Main
{
   ArrayList<Integer> myArray = new ArrayList<Integer>(); 
   int[][] myOtherArray;
   int[] order;
      
   void readfile() throws IOException
   {
        Scanner s = new Scanner(System.in);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num =-1;
        //String line;
        //System.out.println("enter nums.  Enter 0 when done");
        while(num !=0)
        {
            num =  s.nextInt();
            //line = br.readLine();
            //num = Integer.parseInt(line);
            
            myArray.add(num);
        }
   }
   
   void reset(int m)
   {
       int i = 0;
       while (i < myOtherArray.length)
       {
           myOtherArray[i][0] = i+1;
           myOtherArray[i][1] = 0;
           order[i] = 0;
           i++;
       }
   }
   
   int find(int m)
   {
       myOtherArray = new int[m][2];
       order = new int[m];
       int leap = 4;
       reset(m);
      
       while (order[m-1] != 13)
       {
           leap++;
           int index = 0;
           int counter = 1;
           
           reset(m);
           myOtherArray[0][1] = 1;
           order[0] = 1;
           
           while (counter != myOtherArray.length)
           {
              int pointer = 0; 
              while (true)
              {
                  if (pointer == leap) break;
                  index++;
                  
                  if (index < myOtherArray.length && myOtherArray[index][1] == 0) 
                  {
                    pointer++;
                  }
                  else if (index == myOtherArray.length) index = 0;
              }
              myOtherArray[index][1] =  1;
              order[counter] =  myOtherArray[index][0];        
              counter++;
           }
       }     
       return leap;
   } 
           
   public static void main(String[] args)
   {
       Main m = new Main();
       try
       {
               m.readfile();
               for(int i = 0; 1 < m.myArray.size();i++)
               {
                   int num = m.myArray.get(i);
                   if (num != 0)
                    System.out.printf("%d\n",m.find(num));
               }
       }
       catch(Exception e){}
   }
}
