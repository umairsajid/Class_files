// File Queue2.h: Header file for Queue ADT.  
// Class is templated.
#include <iostream>

using namespace std;

template <class ItemType>
struct NodeType;

template <class ItemType>
class QueType
{	template<class T>
	friend ostream& operator<<(ostream& out, const QueType<T>& que);
public:
    QueType();
    // Class constructor.
	QueType(QueType<ItemType> &que);
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
	// item is a copy of the removed element 
	QueType<ItemType> &operator=(const QueType<ItemType> & original);
	int size();
private:
    NodeType<ItemType>* qFront;
    NodeType<ItemType>* qRear;
};

 
// Implementation file for Queue ADT; class specification in file Queue2.h.
// Class is templated.

template <class ItemType>
struct NodeType
{
    ItemType info;
    NodeType* next;
};

template <class ItemType>
QueType<ItemType>::QueType()		// Class constructor.
// Post:  qFront and qRear are set to NULL.
{
    qFront = NULL;
    qRear = NULL;
}

template <class ItemType>
void QueType<ItemType>::MakeEmpty()
// Post: Queue is empty; all elements have been deallocated.
{
    NodeType<ItemType>* tempPtr;

    while (qFront != NULL)
    {
        tempPtr = qFront;
        qFront = qFront->next;
        delete tempPtr;
    }
    qRear = NULL;
}

template <class ItemType>		// Class destructor.
QueType<ItemType>::~QueType()
{
    MakeEmpty();
}
 
template <class ItemType>
bool QueType<ItemType>::IsFull() const
// Returns true if there is no room for another ItemType on the free store;
// false otherwise.
{
    NodeType<ItemType>* ptr;
    ptr = new NodeType<ItemType>;
    if (ptr == NULL)
        return true;
    else
    {
        delete ptr;
        return false;
    }
}

template <class ItemType>
bool QueType<ItemType>::IsEmpty() const
// Returns true if there are no elements on the queue; false otherwise.
{
    return (qFront == NULL);
}

template <class ItemType>
void QueType<ItemType>::AddQ(ItemType newItem)
// Adds newItem to the rear of the queue.
// Pre:  Queue has been initialized and is not full.
// Post: newItem is at rear of queue.

{
    NodeType<ItemType>* newNode;

    newNode = new NodeType<ItemType>;
    newNode->info = newItem;
    newNode->next = NULL;
    if (qRear == NULL)
        qFront = newNode;
    else
        qRear->next = newNode;
    qRear = newNode;
}


template <class ItemType>
void QueType<ItemType>::RemoveQ(ItemType& item)
// Removes front item from the queue and returns it in item.
// Pre:  Queue has been initialized and is not empty.
// Post: Front element has been removed from queue.
//       item is a copy of removed element.
{
    NodeType<ItemType>* tempPtr;

    tempPtr = qFront;
    item = qFront->info;
    qFront = qFront->next;
    if (qFront == NULL)
        qRear = NULL;
    delete tempPtr;
}

template <class ItemType>
QueType<ItemType>::QueType(QueType<ItemType> &que)
{
     NodeType<ItemType> *ptrOrig, *ptrDest;
     ptrOrig = que.qFront;
     if(ptrOrig == NULL)
     {
          this -> qFront = NULL;
	      this -> qRear = NULL;
     }
     else
     {
          ptrDest = new NodeType<ItemType>;
          ptrDest -> info = ptrOrig -> info;
          this  -> qFront = ptrDest;
          ptrOrig = ptrOrig ->  next;

          while(ptrOrig != NULL)
          {
            ptrDest -> next = new NodeType<ItemType>;
            ptrDest = ptrDest -> next;
            ptrDest -> info = ptrOrig -> info;
            ptrOrig = ptrOrig -> next;
          } 

     ptrDest -> next = NULL;
	 this -> qRear = ptrDest;
     }     //cout<<this -> qFront -> next -> next -> next ->next ->info<<endl;
}


template <class ItemType>
QueType<ItemType> &QueType<ItemType>::operator=(const QueType<ItemType> & original)
{

	NodeType<ItemType> *ptrOrig, *ptrDest;

    if (this != &original)               // check that not st = st
    {
                this -> MakeEmpty();            // destroy previous array
                ptrOrig = original.qFront;  
                if(ptrOrig == NULL)
     			{
          			this -> qRear = NULL;
     			}
     			else
     			{
          			ptrDest = new NodeType<ItemType>;
          			ptrDest -> info = ptrOrig -> info;
          			this  -> qFront = ptrDest;
					this  -> qRear = ptrDest;
          			ptrOrig = ptrOrig ->  next;

          			while(ptrOrig != NULL)
          			{
            			ptrDest -> next = new NodeType<ItemType>;
            			ptrDest = ptrDest -> next;
            			ptrDest -> info = ptrOrig -> info;
            			ptrOrig = ptrOrig -> next;
          			} 

     				ptrDest -> next = NULL;
					this -> qRear = ptrDest;
     			} 
	}
    return *this;            // return reference to  this object
}


template <class ItemType>
ostream& operator<<(ostream& out,const QueType<ItemType>& que)
{
        NodeType<ItemType> *tempPtr;
        ItemType item;
        tempPtr = que.qFront;
        while(tempPtr != NULL)
        {
                item = tempPtr -> info;
                out << item << " ";
                tempPtr = tempPtr -> next;
        }       
        return out;
}

template <class ItemType>
int QueType<ItemType>::size()
{
        NodeType<ItemType> *tempPtr;
        int size = 0;

        tempPtr = this -> qFront;
        while(tempPtr != NULL)
        {
                tempPtr = tempPtr -> next;
                size++; 
        }
        return size;
}












