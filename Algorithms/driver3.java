public class driver
{
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
    public static void readfile(String filename, int size) throws IOException
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

    public static int peak(int[] arr, int low, int high)
    {
        int mid = (low + high) / 2;
        if(arr[mid] >= arr[mid - 1] && arr[mid] >= arr[mid + 1])
            return mid;
        else if (arr[mid + 1] > arr[mid])
            return peak(arr, mid + 1, high);
        return peak(arr, low, mid - 1);
    }

    public static void readfile2(String filename, int size) throws IOException
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
    
    pubic static void q2()
    {
    	int[] myArray;
    	int size = count("Q2.txt");
        t.readfile("Q2.txt", size);            
        System.out.println(peak(t.myArray, 0, size));
    }

    public static void main(String[] args)
    {
    	q2();
    }
}