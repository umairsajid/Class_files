#include <iostream>

using namespace std;

int findMax(int, int);

int main()
{
	int firstnum;
	int secondnum;

	while (cin >> firstnum >> secondnum)
	{
		cout << firstnum << " " << secondnum << " " << findMax(firstnum, secondnum)<< endl;
	}
}

int findMax(int num1, int num2)
{

	int max = 0;
    int start;
    int difference;
    if (num1 > num2)
    {
    	int temp = num1;
        num1 = num2;
        num2 = temp;                
    }
    difference = num2 - (num1-1);

   	for (int i = 0; i < difference; i++)
   	{
   		int count = 1;
      	start = num1 + i;
         
      	while (start != 1)
      	{
         	if (start % 2 == 0) start = start / 2;
         	else start = (3 * start) + 1;
         	count++;
      	} 
      	if ((count) > max)
         	max = count;
    
   }
   return max;        
}