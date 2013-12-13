// Header file for Queue ADT; "Queue1.h"
// Class is templated; items are in dynamically allocated storage.

using namespace std;

template<class ItemType>
class QueType
{
public: 
    QueType();
    // Class constructor.
    QueType(int max);
    // Parameterized class constructor.
    ~QueType();
    // Class destructor.
   
 	void MakeEmpty();
    // Function: Initializes the queue to an empty state.
    // Post: Queue is empty.
    bool IsEmpty() const;
    // Function: Determines whether the queue is empty.
    // Post: Function value = (queue is empty)
    bool IsFull() const;
    // Function: Determines whether the queue is full.
    // Post: Function value = (queue is full)
    void AddQ(ItemType newItem);
    // Function: Adds newItem to the rear of the queue.
    // Pre:  Queue is not full.
    // Post: newItem is at the rear of the queue.
    void RemoveQ(ItemType& item);
    // Function: Removes front item from the queue and returns it in item.
    // Pre:  Queue is not empty.
    // Post: Front element has been removed from the queue.
    //       item is a copy of the removed element.
private:
    int front;
    int rear;
    ItemType* items;
    int maxQue;
	int size;
};
//#include "Queue1.cpp"

// Implementation file     Queue1.cpp      for Queue1.h
// Class is templated; array of items is in dynamically allocated storage.
template<class ItemType>
QueType<ItemType>::QueType(int max)	
// Paramaterized class constructor
// Post: maxQue, front, and rear have been initialized.
//       The array to hold the queue elements has been dynamically allocated.
{
    maxQue = max ;
    front = 0 ;
    rear = 0 ;
	size = 0;
    items = new ItemType[maxQue];
}


template<class ItemType>
QueType<ItemType>::QueType()		// Default class constructor
// Post: maxQue, front, and rear have been initialized.
//       The array to hold the queue elements has been dynamically allocated.

{
    maxQue = 500;
    front = 0 ;
    rear = 0 ;
	size = 0;
    items = new ItemType[maxQue];
}
template<class ItemType>
QueType<ItemType>::~QueType()			// Class destructor
{
    delete [] items;
}

template<class ItemType>
void QueType<ItemType>::MakeEmpty()
// Post: front and rear have been reset to the empty state.
{
    front = 0 ;
    rear = 0 ;
}

template<class ItemType>
bool QueType<ItemType>::IsEmpty() const
// Returns true if the queue is empty; false otherwise.
{
    return (size == 0);
}

template<class ItemType>
bool QueType<ItemType>::IsFull() const
// Returns true if the queue is full; false otherwise.
{
    return (size == maxQue);
	//return ((rear + 1) % maxQue == front);
}

template<class ItemType>
void QueType<ItemType>::AddQ(ItemType newItem)
// Post: newItem is at the rear of the queue.
{
		ItemType qItem;
		ItemType *temp;
		int i = 0 ;
		

		if(size == maxQue) 
		{
			maxQue = maxQue * 2;
			temp = new ItemType[maxQue];	
			while(!IsEmpty())
			{
				RemoveQ(qItem);				
				temp[i] = qItem;				
				i++;				
			} 				
			delete[] items;			
			items = temp;			
			front = 0;
			size = rear = maxQue/2;			
		}		
    	items[rear] = newItem;
		size ++;
		rear = (rear + 1) % maxQue;
		
}
template<class ItemType>
void QueType<ItemType>::RemoveQ(ItemType& item)
// Post: The front of the queue has been removed and a copy returned in item.
{
    	item = items[front];
		front = (front + 1) % maxQue;
		size --;
    
}
