//
//import java.util.*; 
//public class problem1 {
//
//	ArrayList<Integer> list; 
//	
//	public void graph(int [] a){
//	
//	boolean valid = true; 
//	for(int i = 0; i < a.length; i++)
//	{
//		if(a[i] < 0)
//		{
//			valid = false;
//			break; 
//		}			
//
//	else{
//		int current = a[0]; 
//		for(int i = 0; i < a.length; i++)
//		{
//			if(a[i] > current)
//			{
//				current = a[i];	
//				if(current == 0)
//					{
//						System.out.println("Yes"); 
//					}
//					else{
//						
//					}
//			}
//		} 
//		
//	}
//	}
//	}
//}
//	
//	
////	
////
////	public void isGraph(int [] a)
////	{
////		if(isValid(a) == true)
////		{
////			if(getMaxElement(a) == 0)
////			{
////				System.out.println("Yes"); 
////			}
////			else 
////			{
////				int currentMax = getMax(a);
////				a = greedy(a, currentMax);
////				list.clear(); 
////				isGraph(a);
////			}
////		}
////		else
////		{
////			System.out.println("No");  
////		}
////	}
////
////	public boolean isValid(int [] a)
////	{
////		boolean valid = true; 
////		for(int i = 0; i < a.length; i++)
////		{
////			if(a[i] < 0)
////			{
////				valid = false;
////				break; 
////			}			
////		}
////		return valid; 
////
////
////	}
////
////	public int getMaxElement(int [] a)
////	{
////		int current = a[0]; 
////		for(int i = 0; i < a.length; i++)
////		{
////			if(a[i] > current)
////			{
////				current = a[i];				
////			}
////		}
////		return current; 
////	}
////
////
////
////	public int getMax(int [] a)
////	{
////		int current = 0;
////		int currentIndex = 0; 
////		for(int i = 0; i < a.length; i++)
////		{
////			if(a[i] > current)
////			{
////				current = a[i];
////				currentIndex = i; 
////			}
////		}
////		return currentIndex; 
////	}
////
////
////
////	public int[] greedy(int [] a, int currentMax)
////	{
////		list = new ArrayList<Integer>(); 
////		while(a[currentMax] > 0)
////		{
////			int next = getNextMax(a, currentMax); 
////			a[currentMax] = a[currentMax] - 1;
////			a[next] = a[next] - 1; 
////			list.add(next); 
////			//print(a);
////		}
////		return a; 
////
////	}
////
////	public void print(int [] a)
////	{
////		for(int i = 0; i < a.length; i++)
////		{
////			System.out.print(a[i] + " "); 
////		}
////		System.out.println(); 
////	}
////
////	public int getNextMax(int [] a, int currentMax)
////	{
////		int current = 0;
////		int currentIndex = 0; 
////		for(int i = 0; i < a.length; i++)
////		{
////			if(i != currentMax && (list.contains(i) == false))
////			{				
////				if(a[i] > current)
////				{
////					current = a[i];
////					currentIndex = i; 
////				}
////			}
////
////		}
////		return currentIndex; 
////	}
//
