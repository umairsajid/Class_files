
// CS 271 Data Structures
// 
// Lab 8 orderedLinkedList.h

//   Ordered Linked List implementation    orderedLinkedList.h   file

#ifndef H_orderedLinkedListType
#define H_orderedLinkedListType

#include <iostream>
#include <cassert>

#include "linkedList.h"

using namespace std;

template<class Type>
class orderedLinkedListType: public linkedListType<Type>
{
public:
   bool search(const Type& searchItem);
     //Function to determine whether searchItem is in the list.
     //Postcondition: Returns true if searchItem is found in
     //               the list; otherwise, it returns false

   void insertNode(const Type& newItem);
     //Function to insert newItem in the list.
     //Postcondition: first points to the new list and newItem is 
     //               inserted at the proper place in the list.

   void deleteNode(const Type& deleteItem);
     //Function to delete deleteItem from the list.
     //Postcondition: If found, the node containing deleteItem
     //               is deleted from the list; first points 
     //               to the first node of the new list.
     //               If deleteItem is not in the list, an 
     //               appropriate message is printed.
     //   void mergeLists(orderedLinkedListType<Type> &list1, orderedLinkedListType<Type> 		&list2) ;
     //Fucntion will take in two lists and combine them to create
     // one single list
	
	void mergeLists(orderedLinkedListType<Type> &list1,
				    orderedLinkedListType<Type> &list2);
};

template<class Type>
bool orderedLinkedListType<Type>::search(const Type& searchItem)
{
    bool found;
    nodeType<Type> *current; //pointer to traverse the list
	
    found = false;    //initialize found to false
    current = linkedListType<Type>::first;  //start the search at the first node

    while(current != NULL && !found)
       if(current->info >= searchItem)
          found = true;
       else
          current = current->link;

      if(found)       
         found = (current->info == searchItem); //test for equality

    return found;
}//end search


template<class Type>
void orderedLinkedListType<Type>::insertNode(const Type& newItem)
{
	nodeType<Type> *current; //pointer to traverse the list
	nodeType<Type> *trailCurrent; //pointer just before current
	nodeType<Type> *newNode;  //pointer to create a node
	nodeType<Type> *tempPtr = linkedListType<Type>::first;

	bool  found, contained = false;

	newNode = new nodeType<Type>; //create the node
 	assert(newNode != NULL);

	newNode->info = newItem;   //store newitem in the node
	newNode->link = NULL;      //set the link field of the node 
	                           //to NULL
	
	while(tempPtr != NULL && !contained)
	{
		if(tempPtr->info == newItem)
			contained = true;
		tempPtr = tempPtr->link;
	}

	if(contained)
		cerr << "item is already in list\n";

	if(linkedListType<Type>::first == NULL)  		//Case 1	
	{	
	   linkedListType<Type>::first = newNode;
	   linkedListType<Type>::count++;
 	}
	else if(!contained)
	{
	   current = linkedListType<Type>::first;
	   found = false;

	   while(current != NULL && !found) 	//search the list
			if(current->info >= newItem)
				found = true;
     			else
			{
				trailCurrent = current;
				current = current->link;
			}
		  
	   if(current == linkedListType<Type>::first)  	//Case 2
	   {
			newNode->link = linkedListType<Type>::first;
			linkedListType<Type>::first = newNode;
 			linkedListType<Type>::count++;
	   }
	   else				//Case 3
	   {
			trailCurrent->link = newNode;
			newNode->link = current;
 			linkedListType<Type>::count++;
	   }
	}//end else
}//end insertNode

template<class Type>
void orderedLinkedListType<Type>::deleteNode
                                    (const Type& deleteItem)
{
	nodeType<Type> *current; //pointer to traverse the list
	nodeType<Type> *trailCurrent; //pointer just before current
	bool found;

	if(linkedListType<Type>::first == NULL) 		//Case 1
		cerr<<"Cannot delete from an empty list."<<endl;
	else
	{
		current = linkedListType<Type>::first;
		found = false;

		while(current != NULL && !found)  //search the list
			if(current->info >= deleteItem)
				found = true;
			else
			{
				trailCurrent = current;
				current = current->link;
			}
		
		if(current == NULL)   			//Case 4
			cout<<"The item to be deleted is not in the list."
			    <<endl;
		else
 			if(current->info == deleteItem) //item to be deleted  
									//is in the list
			{
  				if(linkedListType<Type>::first == current) 		//Case 2
				{
					linkedListType<Type>::first = 							linkedListType<Type>::first->link;

					delete current;
				}
				else     				//Case 3
				{
					trailCurrent->link = current->link;
					delete current;
				}
 				linkedListType<Type>::count--;
			}
			else  					//Case 4
				cout<<"The item to be deleted is not in the list."
				    <<endl;
	}
	
} //end deleteNode

template<class Type>
void orderedLinkedListType<Type>::mergeLists(orderedLinkedListType<Type> &list1,
                                    orderedLinkedListType<Type> &list2)
{
    nodeType<Type> *list1Ptr = list1.first, *list2Ptr = list2.first, *tempPtr = new nodeType<Type>;
    linkedListType<Type>::destroyList();
	
    while (list1Ptr != NULL && list2Ptr != NULL)
	{
		if(list1Ptr -> info < list2Ptr -> info  || list1Ptr != NULL && list2Ptr == NULL)
		{
			tempPtr -> link = list1Ptr;
			tempPtr = tempPtr -> link;
			list1Ptr = list1Ptr -> link;
		}
		else if(list1Ptr -> info > list2Ptr -> info )
		{
			tempPtr -> link = list2Ptr;
			tempPtr = tempPtr -> link;
			list2Ptr = list2Ptr -> link;
		} 
		else if (list1Ptr -> info == list2Ptr -> info)
		{
			tempPtr -> link = list2Ptr;
			tempPtr = tempPtr -> link;
			list2Ptr = list2Ptr -> link;
			list1Ptr = list1Ptr -> link;
		}
		if (linkedListType<Type>::first == NULL)
			linkedListType<Type>::first = tempPtr;
	}
	while (list1Ptr == NULL && list2Ptr != NULL)
	{
		tempPtr -> link = list2Ptr;
		tempPtr = tempPtr -> link;
		list2Ptr = list2Ptr -> link;
		if (linkedListType<Type>::first == NULL)
			linkedListType<Type>::first = tempPtr;
	}      
	while (list1Ptr != NULL && list2Ptr == NULL)   
    {
		tempPtr -> link = list1Ptr;
		tempPtr = tempPtr -> link;
		list1Ptr = list1Ptr -> link;
		if (linkedListType<Type>::first == NULL)
			linkedListType<Type>::first = tempPtr;
	}  
	linkedListType<Type>::last = tempPtr;
	linkedListType<Type>::last -> link = NULL;
	list1.first = list1.last = list2.first = list2.last = NULL;
		 
		
}

//--- Definition mergeLists


#endif

