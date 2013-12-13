/*Course: CSC 271
   Due Date: Feb 6, 2013
   File Name: lab2.cpp
   Description:  This program receives 10 numbers from the user and puts 
                 them in order from lowest to highest and highest to lowest 
                 pointer arrays.
*/

#include <iostream>
#include <iomanip>

using namespace std;

const int maxNum = 10;

int main()
{
        int list[maxNum];
        int *listLowest[maxNum];
        int *listHighest[maxNum];
        
        for (int i = 0; i < maxNum; i++)
        {
             cout << "Enter number " << i+1 << ":";
             cin >> list[i];
             listLowest[i] = &list[i];
             listHighest[i] = &list[i];
        }
        
        for (int i = 1; i < maxNum; i++)
        {
            int *currElement = listLowest[i];
            int j = i-1;
            while(j>=0 && *listLowest[j] > *currElement)
            {
               listLowest[j+1] = listLowest[j];
               j-=1;
            }
            listLowest[j+1] = currElement;
           
        }
        
        for (int i = 1; i <= maxNum; i++)
        {
            listHighest[maxNum-i] = listLowest[i-1];
        }    

        cout <<endl<<setw(17)<<"Original List"<<setw(21)<<"Low - High"
             <<setw(25)<<"High - Low"<<endl; 
        for (int i = 0; i < maxNum; i++)
        {
            cout <<"\t"<<list[i]<<"\t\t\t"<<*listLowest[i]
                 <<"\t\t\t"<<*listHighest[i]<<endl;
       
        }
        cout<<endl;
        system("pause");
}
