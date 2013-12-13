//Program to test variosus operations on an ordered linked list
//         testProgLinkedList.cpp      file

#include <iostream> 
#include "orderedLinkedList.h"

using namespace std;

int main()
{
     orderedLinkedListType<int> list1, list2, list3;			
     int num, i;								

     for (i = 1; i < 9; i+=2)
     {
         list1.insertNode(i);
     }
     for (i = 0; i < 9; i+=2)
     {
         list2.insertNode(i);
     }
     cout<<list1<<endl;
     cout<<list2<<endl;
     list3.mergeLists(list2, list1);
	 cout<<list3<<endl;
	 cout<<list1<<endl;
	 cout<<list2<<endl;
     return 0;					
}
